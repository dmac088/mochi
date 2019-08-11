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
	public NavFacetResult findAll(String locale, String currency) {
		NavFacetContainer nfc = new NavFacetContainer();
		NavFacetResult nfr = new NavFacetResult();
		
		nfc.setCategories(	categoryService.findAll(locale).stream().map(c -> {
									NavFacet<Category> cnf = this.convertCatToNavFacet(c);
									cnf.setFacetMaxMarkdownPrice(productService.getMaxPriceForCategory(c, currency));	
									cnf.setFacetProductCount(productService.getCountForCategory(c));
									return cnf;
							}).collect(Collectors.toList()).stream()
							  .filter(nf -> nf.getFacetProductCount() > 0)
							  .collect(Collectors.toList()));
		nfr.setResult(nfc);
		return nfr;
		
	}
	
	
	@Override
	public NavFacetResult findAll(String locale, String currency, String categoryDesc, NavFacetContainer selectedFacets) {
		List<Tag> lt = selectedFacets.getTags().stream().map(t -> t.getPayload()).collect(Collectors.toList());
		List<Brand> lb = selectedFacets.getBrands().stream().map(t -> t.getPayload()).collect(Collectors.toList());
		List<Category> lc = selectedFacets.getCategories().stream().map(t -> t.getPayload()).collect(Collectors.toList());
		
		NavFacetContainer nfc = new NavFacetContainer();
		NavFacetResult nfr = new NavFacetResult();
		
		List<Category> categories = categoryService.findAll(locale, categoryDesc, lb, lt);
		List<Brand> brands = brandService.findAll(locale, currency, categoryDesc, lc, lt);
		List<Tag> tags = tagService.findAll(locale, currency, categoryDesc, lc, lb);
	
		List<NavFacet<Category>> catBars = categories.stream().map(c -> {
			NavFacet<Category> s = convertCatToNavFacet(c);
			s.setFacetProductCount(this.getCountForCategory(locale, currency, categoryDesc, c, lb, lt));
			return s;
		}).collect(Collectors.toList()).stream()
			.filter(c -> c.getFacetProductCount() > 0)
			.collect(Collectors.toList());
	
		List<NavFacet<Brand>> brandBars = brands.stream().map(b -> {
			NavFacet<Brand> s = convertBrandToNavFacet(b);
			s.setFacetProductCount(this.getCountForBrand(locale, currency, categoryDesc, lc, b, lt));
			return s;
		}).collect(Collectors.toList()).stream()
			.filter(c -> c.getFacetProductCount() > 0)
			.collect(Collectors.toList());
		
		List<NavFacet<Tag>> tagBars = tags.stream().map(t -> {
			List<Long> tagIds = new ArrayList<Long>();
			NavFacet<Tag> s = convertTagToNavFacet(t);
			s.setFacetProductCount(this.getCountForTag(locale, currency, categoryDesc, lc, lb, t));
			return s;
		}).collect(Collectors.toList()).stream()
			.filter(t -> t.getFacetProductCount() > 0)
			.collect(Collectors.toList());
		
		nfc.setBrands(brandBars);
		nfc.setCategories(catBars);
		nfc.setTags(tagBars);
		
		nfr.setResult(nfc);
		
		return nfr;
		
	}
	
	private Long getCountForBrand(String locale, String currency, String category, List<Category> categories, Brand brand, List<Tag> tags) {
		List<Brand> brands = new ArrayList<Brand>();
		brands.add(brand);
		
		return productService.getCount(
		CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
		category, 
		locale, 
		currency, 
		ProductVars.ACTIVE_SKU_CODE,
		categories, 
		brands, 
		tags
		);
	}
	
	
	private Long getCountForTag(String locale, String currency, String category, List<Category> categories, List<Brand> brands, Tag tag) {
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(tag);
		
		return productService.getCount(
		CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
		category, 
		locale, 
		currency, 
		ProductVars.ACTIVE_SKU_CODE,
		categories, 
		brands, 
		tags
		);
	}
	
	private Long getCountForCategory(String locale, String currency, String categoryDesc, Category category, List<Brand> brands, List<Tag> tags) {
		List<Category> categories = new ArrayList<Category>();
		categories.add(category);
		
		return productService.getCount(
		CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
		categoryDesc, 
		locale, 
		currency, 
		ProductVars.ACTIVE_SKU_CODE,
		categories, 
		brands, 
		tags
		);
	}

	@Override
    public NavFacet<Category> convertCatToNavFacet(final Category c) {
    	final NavFacet<Category> s = new NavFacet<Category>();
    	s.setFacetClassName(c.getClass().getSimpleName());
    	s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
    	s.setFacetId(c.getCategoryCode().toString());
    	s.setFacetParentId(c.getParentId());
    	s.setFacetChildCount(c.getChildCategoryCount());
    	s.setFacetDisplayValue(c.getCategoryDesc());
    	s.setToken(c.getCategoryCode());
    	s.setFacetLevel(c.getCategoryLevel());
    	s.setPayload(c);
		return s;
    } 
	
    @Override
	public NavFacet<Tag> convertTagToNavFacet(Tag t) {
		NavFacet<Tag> s = new NavFacet<Tag>();
		s.setFacetClassName(t.getClass().getSimpleName());
		s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
		s.setFacetId(t.getTagId().toString());
		s.setFacetParentId(new Long(-1));
		s.setFacetChildCount(new Long(0));
		s.setFacetDisplayValue(t.getTagDesc());
		s.setToken(t.getTagDesc());
		s.setFacetLevel(new Long(0));
		s.setPayload(t);
		return s;
	}
	
    @Override
    public NavFacet<Brand> convertBrandToNavFacet(final Brand b) {
    	final NavFacet<Brand> s = new NavFacet<Brand>();
    	s.setFacetClassName(b.getClass().getSimpleName());
    	s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
    	s.setFacetId(b.getBrandCode());
    	s.setFacetParentId(new Long(-1));
    	s.setFacetChildCount(new Long(0));
    	s.setFacetDisplayValue(b.getBrandDesc());
    	s.setToken(b.getBrandCode());
    	s.setFacetLevel(new Long(0));
    	s.setPayload(b);
		return s;
    }





}
