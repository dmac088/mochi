package io.nzbee.ui.component.web.facet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Tag;
import io.nzbee.domain.services.brand.IBrandService;
import io.nzbee.domain.services.category.ICategoryService;
import io.nzbee.domain.services.product.IProductService;
import io.nzbee.domain.services.tag.ITagService;
import io.nzbee.entity.brand.attribute.BrandAttribute_;
import io.nzbee.entity.category.attribute.CategoryAttribute_;
import io.nzbee.entity.product.tag.attribute.ProductTagAttribute_;
import io.nzbee.ui.component.web.generic.UIService;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;

@Service(value = "SidebarService")
@Transactional
//@CacheConfig(cacheNames="products")
public class NavFacetServiceImpl extends UIService implements INavFacetService {

	@Autowired
	private ITagService tagService;
	
	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IBrandService brandService;

	@Autowired
	@Qualifier("productDomainService")
	private IProductService productService;

	
	@Override
	public List<NavFacet<Tag>> findAllTags(String locale, String currency, String category, List<NavFacet> selectedFacets) {
		// TODO Auto-generated method stub
		List<Long> categoryIds = super.getFacetIds(selectedFacets, Category.class);
		List<Long> brandIds = super.getFacetIds(selectedFacets, Brand.class);
		
	
		
		List<Tag> tags = tagService.findAll(locale, currency, category, categoryIds, brandIds);
		
		List<NavFacet<Tag>> tagBars = tags.stream().map(t -> {
				List<Long> tagIds = new ArrayList<Long>();
				tagIds.add(t.getTagId());
				NavFacet<Tag> s = convertTagToSidebar(t);
				
				s.setProductCount(productService.getCount(
						CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
						category, 
						locale, 
						currency, 
						ProductVars.ACTIVE_SKU_CODE, 
						categoryIds, 
						brandIds, 
						tagIds
						)
				);
				return s;
			}).collect(Collectors.toList())
				.stream().filter(t -> t.getProductCount() > 0).collect(Collectors.toList());
		
		return tagBars;
	}

	private NavFacet<Tag> convertTagToSidebar(Tag t) {
		NavFacet<Tag> s = new NavFacet<Tag>();
		s.setId(t.getTagId());
		s.setToken(t.getTagCode());
		s.setFacetingName(CategoryVars.TAG_FACET_NAME);
		s.setFieldName(ProductTagAttribute_.tagDesc.getName());
		return s;
	}
	
	@Override
	public List<NavFacet<Category>> findAllCategories(String locale, String currency, String category, List<NavFacet> selectedFacets) {
		// TODO Auto-generated method stub
		List<Long> tagIds = super.getFacetIds(selectedFacets, Tag.class);
		List<Long> brandIds = super.getFacetIds(selectedFacets, Brand.class);
		
		List<Category> categories = categoryService.findAll(locale, category, brandIds, tagIds);
		
		List<NavFacet<Category>> catBars = categories.stream().map(c -> convertCatToSidebar(c)).collect(Collectors.toList());
		
		return catBars;
	}
	
	 //Create a data transfer object
    private NavFacet<Category> convertCatToSidebar(final Category c) {
    	final NavFacet<Category> s = new NavFacet<Category>();
    	s.setFacetingName(CategoryVars.PRIMARY_CATEGORY_FACET_NAME);
    	s.setFieldName(CategoryAttribute_.categoryDesc.getName());
    	s.setToken(c.getCategoryCode());
    	s.setId(c.getCategoryId());
    	s.setProductCount(c.getProductCount());
		return s;
    } 

	@Override
	public List<NavFacet<Brand>> findAllBrands(String locale, String currency, String category, List<NavFacet> selectedFacets) {
		// TODO Auto-generated method stub
		List<Long> tagIds = super.getFacetIds(selectedFacets, Tag.class);
		List<Long> categoryIds = super.getFacetIds(selectedFacets, Category.class);
		
		List<Brand> brands = brandService.findAll(locale, currency, category, categoryIds, tagIds);
		
		List<NavFacet<Brand>> brandBars = brands.stream().map(b -> convertBrandToSidebar(b)).collect(Collectors.toList())
										.stream().filter(b -> b.getProductCount() > 0).collect(Collectors.toList());
		return brandBars;
	}
	
	 //Create a data transfer object
    private NavFacet<Brand> convertBrandToSidebar(final Brand b) {
    	final NavFacet<Brand> s = new NavFacet<Brand>();
    	s.setFacetingName(CategoryVars.BRAND_FACET_NAME);
    	s.setFieldName(BrandAttribute_.brandDesc.getName());
    	s.setToken(b.getBrandCode());
    	s.setId(b.getBrandId());
    	s.setProductCount(b.getProductCount());
		return s;
    }


}
