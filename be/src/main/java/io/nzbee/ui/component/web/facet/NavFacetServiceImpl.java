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
		
		nfc.getFacets().addAll(	categoryService.findAll(locale).stream().map(c -> {
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
	public NavFacetResult findAllBrands(String locale, String category) {
		List<Brand> brands = brandService.findAll(category, locale);
		
		NavFacetContainer nfc = new NavFacetContainer();
		NavFacetResult nfr = new NavFacetResult();
		
		List<NavFacet<Brand>> brandBars = brands.stream().map(b -> {
			NavFacet<Brand> s = convertBrandToNavFacet(b);
			return s;
		}).collect(Collectors.toList());
		
		nfc.getFacets().addAll(brandBars);
		
		nfr.setResult(nfc);
		
		return nfr;
	}
	
	@Override
	public NavFacetResult findAll(String locale, String currency, String categoryDesc, NavFacetContainer selectedFacets) {
		List<Tag> lt = selectedFacets.getTags().stream().map(t -> t.getPayload()).collect(Collectors.toList());
		List<Brand> lb = selectedFacets.getBrands().stream().map(t -> t.getPayload()).collect(Collectors.toList());
		List<Category> lc = selectedFacets.getProductCategories().stream().map(t -> t.getPayload()).collect(Collectors.toList());
		
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
			NavFacet<Tag> s = convertTagToNavFacet(t);
			s.setFacetProductCount(this.getCountForTag(locale, currency, categoryDesc, lc, lb, t));
			return s;
		}).collect(Collectors.toList()).stream()
			.filter(t -> t.getFacetProductCount() > 0)
			.collect(Collectors.toList());
		
		nfc.getFacets().addAll(brandBars);
		nfc.getFacets().addAll(catBars);
		nfc.getFacets().addAll(tagBars);
		
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
    	s.setFacetId(calcFacetId(s.getFacetClassName(), c.getCategoryCode().toString()));
    	s.setFacetParentId(c.getParentCode());
    	s.setFacetChildCount(c.getChildCategoryCount());
    	s.setFacetDisplayValue(c.getCategoryDesc());
    	s.setToken(calcToken(s.getFacetClassName(), c.getCategoryCode()));
    	s.setFacetLevel(c.getCategoryLevel());
    	s.setPayload(c);
		return s;
    } 
	
    @Override
	public NavFacet<Tag> convertTagToNavFacet(Tag t) {
		NavFacet<Tag> s = new NavFacet<Tag>();
		s.setFacetClassName(t.getClass().getSimpleName());
		s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
		s.setFacetId(calcFacetId(s.getFacetClassName(), t.getTagCode()));
		s.setFacetChildCount(new Long(0));
		s.setFacetDisplayValue(t.getTagDesc());
		s.setToken(calcToken(s.getFacetClassName(), t.getTagCode()));
		s.setFacetLevel(new Long(0));
		s.setPayload(t);
		return s;
	}
	
    @Override
    public NavFacet<Brand> convertBrandToNavFacet(final Brand b) {
    	final NavFacet<Brand> s = new NavFacet<Brand>();
    	s.setFacetClassName(b.getClass().getSimpleName());
    	s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
    	s.setFacetId(calcFacetId(s.getFacetClassName(), b.getBrandCode()));
    	s.setFacetChildCount(new Long(0));
    	s.setFacetDisplayValue(b.getBrandDesc());
    	s.setToken(calcToken(s.getFacetClassName(), b.getBrandCode()));
    	s.setFacetLevel(new Long(0));
    	s.setPayload(b);
		return s;
    }
    
    @Override
    public String calcFacetId(String className, String id) {
    	return className + ".id" + "[" + id + "]";
    }
    
    @Override
    public String calcToken(String className, String id) {
    	return className + ".token" + "[" + id + "]";
    }

}
