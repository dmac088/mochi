package io.nzbee.ui.component.web.sidebar;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Tag;
import io.nzbee.domain.services.brand.IBrandService;
import io.nzbee.domain.services.category.ICategoryService;
import io.nzbee.domain.services.tag.ITagService;
import io.nzbee.entity.brand.attribute.BrandAttribute_;
import io.nzbee.entity.category.attribute.CategoryAttribute_;
import io.nzbee.entity.product.tag.attribute.ProductTagAttribute_;
import io.nzbee.ui.component.web.generic.UIService;
import io.nzbee.variables.CategoryVars;

@Service(value = "SidebarService")
@Transactional
//@CacheConfig(cacheNames="products")
public class SidebarServiceImpl extends UIService implements ISidebarService {

	@Autowired
	private ITagService tagService;
	
	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IBrandService brandService;
	
	@Override
	public List<Sidebar> findAllTags(String locale, String currency, String category, List<Sidebar> selectedFacets) {
		// TODO Auto-generated method stub
		List<Long> categoryIds = super.getFacetIds(selectedFacets, Category.class);
		List<Long> brandIds = super.getFacetIds(selectedFacets, Brand.class);
		
		List<Tag> tags = tagService.findAll(locale, currency, category, categoryIds, brandIds);
		
		List<Sidebar> tagBars = tags.stream().map(t -> convertTagToSidebar(t)).collect(Collectors.toList());
		
		return tagBars;
	}

	private Sidebar convertTagToSidebar(Tag t) {
		Sidebar s = new Sidebar();
		s.setId(t.getTagId());
		s.setToken(t.getTagCode());
		s.setDesc(t.getTagDesc());
		s.setFacetingName(CategoryVars.TAG_FACET_NAME);
		s.setFieldName(ProductTagAttribute_.tagDesc.getName());
		s.setFacetingClassName(t.getClass().getSimpleName());
		s.setProductCount(t.getProductCount());
		return s;
	}
	
	@Override
	public List<Sidebar> findAllCategories(String locale, String currency, String category, List<Sidebar> selectedFacets) {
		// TODO Auto-generated method stub
		List<Long> tagIds = super.getFacetIds(selectedFacets, Tag.class);
		List<Long> brandIds = super.getFacetIds(selectedFacets, Brand.class);
		
		List<Category> categories = categoryService.findAll(locale, currency, category, brandIds, tagIds);
		
		List<Sidebar> catBars = categories.stream().map(c -> convertCatToSidebar(c)).collect(Collectors.toList());
		
		return catBars;
	}
	
	 //Create a data transfer object
    private Sidebar convertCatToSidebar(final Category c) {
    	final Sidebar s = new Sidebar();
    	s.setFacetingClassName(c.getClass().getSimpleName());
    	s.setFacetingName(CategoryVars.PRIMARY_CATEGORY_FACET_NAME);
    	s.setFieldName(CategoryAttribute_.categoryDesc.getName());
    	s.setToken(c.getCategoryCode());
    	s.setLevel(new Long(0));
    	s.setDesc(c.getCategoryDesc());
    	s.setId(c.getCategoryId());
    	s.setProductCount(c.getProductCount());
    	s.setParent(c.getChildCategoryCount() > 0);
		return s;
    }

	@Override
	public List<Sidebar> findAllBrands(String locale, String currency, String category, List<Sidebar> selectedFacets) {
		// TODO Auto-generated method stub
		List<Long> tagIds = super.getFacetIds(selectedFacets, Tag.class);
		List<Long> categoryIds = super.getFacetIds(selectedFacets, Category.class);
		
		List<Brand> brands = brandService.getBrands(locale, currency, category, categoryIds, tagIds);
		
		List<Sidebar> brandBars = brands.stream().map(b -> convertBrandToSidebar(b)).collect(Collectors.toList());
		
		return brandBars;
	}
	
	
	 //Create a data transfer object
    private Sidebar convertBrandToSidebar(final Brand b) {
    	final Sidebar s = new Sidebar();
    	s.setFacetingClassName(b.getClass().getSimpleName());
    	s.setFacetingName(CategoryVars.BRAND_FACET_NAME);
    	s.setFieldName(BrandAttribute_.brandDesc.getName());
    	s.setToken(b.getBrandCode());
    	s.setLevel(new Long(0));
    	s.setDesc(b.getBrandDesc());
    	s.setId(b.getBrandId());
    	s.setProductCount(b.getProductCount());
		return s;
    }


}
