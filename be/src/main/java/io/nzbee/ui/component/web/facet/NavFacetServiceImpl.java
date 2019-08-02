package io.nzbee.ui.component.web.facet;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

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
	public List<NavFacet<Category>> findAllCategories(String lcl, String currencyCode) {
		return categoryService.findAll(lcl).stream().map(c -> {
			NavFacet<Category> cnf = this.convertCatToNavFacet(c);
			cnf.setMaxMarkdownPrice(productService.getMaxPriceForCategory(c.getCategoryId(), currencyCode));	
			cnf.setProductCount(productService.getCountForCategory(c.getCategoryId()));
			return cnf;
		}).collect(Collectors.toList())
		.stream().filter(nf -> {
			return nf.getProductCount() > 0;
		}).collect(Collectors.toList());
	}
	
	@Override
	public List<NavFacet<Tag>> findAllTags(String locale, String currency, String category, List<NavFacet> selectedFacets) {
		// TODO Auto-generated method stub
		List<Long> categoryIds = super.getFacetIds(selectedFacets, Category.class);
		List<Long> brandIds = super.getFacetIds(selectedFacets, Brand.class);
	
		List<Tag> tags = tagService.findAll(locale, currency, category, categoryIds, brandIds);
		
		List<NavFacet<Tag>> tagBars = tags.stream().map(t -> {
				List<Long> tagIds = new ArrayList<Long>();
				tagIds.add(t.getTagId());
				NavFacet<Tag> s = convertTagToNavFacet(t);
				
				s.setProductCount(
					productService.getCount(
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
				.stream().filter(t -> t.getProductCount() > 0)
				.collect(Collectors.toList());
		
		tagBars.sort((o1, o2) -> o2.getProductCount().compareTo(o1.getProductCount()));
		
		return tagBars;
	}

	private NavFacet<Tag> convertTagToNavFacet(Tag t) {
		NavFacet<Tag> s = new NavFacet<Tag>();
		s.setFacetClassName(t.getClass().getSimpleName());
		s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
		s.setId(t.getTagId());
		s.setFacetDisplayValue(t.getTagDesc());
		s.setToken(t.getTagCode());
		s.setFacetName(CategoryVars.TAG_FACET_NAME);
		s.setPayload(t);
		return s;
	}
	
	@Override
	public List<NavFacet<Category>> findAllCategories(String locale, String currency, String category, List<NavFacet> selectedFacets) {
		// TODO Auto-generated method stub
		List<Long> tagIds = super.getFacetIds(selectedFacets, Tag.class);
		List<Long> brandIds = super.getFacetIds(selectedFacets, Brand.class);
		
		List<Category> categories = categoryService.findAll(locale, category, brandIds, tagIds);
		
		List<NavFacet<Category>> catBars = categories.stream().map(c -> {
			List<Long> categoryIds = new ArrayList<Long>();
			categoryIds.add(c.getCategoryId());
			NavFacet<Category> s = convertCatToNavFacet(c);
			
			s.setProductCount(
				productService.getCount(
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
			.stream().filter(c -> c.getProductCount() > 0)
			.collect(Collectors.toList());
		
		catBars.sort((o1, o2) -> o2.getProductCount().compareTo(o1.getProductCount()));
		
		return catBars;
	}
	 
	 //Create a data transfer object
    private NavFacet<Category> convertCatToNavFacet(final Category c) {
    	final NavFacet<Category> s = new NavFacet<Category>();
    	s.setFacetClassName(c.getClass().getSimpleName());
    	s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
    	s.setId(c.getCategoryId());
    	s.setFacetDisplayValue(c.getCategoryDesc());
    	s.setFacetName(CategoryVars.PRIMARY_CATEGORY_FACET_NAME);
    	s.setToken(c.getCategoryCode());
    	s.setPayload(c);
		return s;
    } 

	@Override
	public List<NavFacet<Brand>> findAllBrands(String locale, String currency, String category, List<NavFacet> selectedFacets) {
		// TODO Auto-generated method stub
		List<Long> tagIds = super.getFacetIds(selectedFacets, Tag.class);
		List<Long> categoryIds = super.getFacetIds(selectedFacets, Category.class);
		
		List<Brand> brands = brandService.findAll(locale, currency, category, categoryIds, tagIds);
		
		List<NavFacet<Brand>> brandBars = brands.stream().map(b -> {
			List<Long> brandIds = new ArrayList<Long>();
			brandIds.add(b.getBrandId());
			NavFacet<Brand> s = convertBrandToNavFacet(b);
			
			s.setProductCount(
				productService.getCount(
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
			.stream().filter(c -> c.getProductCount() > 0)
			.collect(Collectors.toList());
		
		brandBars.sort((o1, o2) -> o2.getProductCount().compareTo(o1.getProductCount()));
		
		return brandBars;
	}
	
	 //Create a data transfer object
    private NavFacet<Brand> convertBrandToNavFacet(final Brand b) {
    	final NavFacet<Brand> s = new NavFacet<Brand>();
    	s.setFacetClassName(b.getClass().getSimpleName());
    	s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
    	s.setId(b.getBrandId());
    	s.setFacetDisplayValue(b.getBrandDesc());
    	s.setFacetName(CategoryVars.BRAND_FACET_NAME);
    	s.setToken(b.getBrandCode());
    	s.setPayload(b);
		return s;
    }


}
