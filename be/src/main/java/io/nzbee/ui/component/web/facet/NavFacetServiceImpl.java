package io.nzbee.ui.component.web.facet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.brand.IBrandService;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.category.ICategoryService;
import io.nzbee.dto.product.IProductService;
import io.nzbee.dto.tag.ITagService;
import io.nzbee.dto.tag.Tag;
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
									return cnf;
							})
							//.filter(c -> c.getFacetProductCount() > 0)
							.sorted(Comparator.comparing(NavFacet::getFacetProductCount
									, (s1, s2) -> {
							            return s2.compareTo(s1);
							        }))
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
		boolean allNUll = tags.isEmpty() && brands.isEmpty() && categories.isEmpty();
	
		List<NavFacet<Category>> catBars = categories.stream().map(c -> {
			NavFacet<Category> s = convertCatToNavFacet(c);
			s.setFacetProductCount(c.getCount());
			return s;
		}).collect(Collectors.toList()).stream()
			.filter(c -> c.getFacetProductCount() > 0)
			.sorted(Comparator.comparing(NavFacet::getFacetProductCount
																			, (s1, s2) -> {
																	            return s2.compareTo(s1);
																	        }))
			.collect(Collectors.toList());
	
		List<NavFacet<Brand>> brandBars = brands.stream().map(b -> {
			NavFacet<Brand> s = convertBrandToNavFacet(b);
			s.setFacetProductCount(this.getCountForBrand(locale, currency, categoryDesc, lc, b, lt));
			return s;
		}).collect(Collectors.toList()).stream()
			.filter(c -> c.getFacetProductCount() > 0)
			.sorted(Comparator.comparing(NavFacet::getFacetProductCount
					, (s1, s2) -> {
			            return s2.compareTo(s1);
			        }))
			.collect(Collectors.toList());
		
		List<NavFacet<Tag>> tagBars = tags.stream().map(t -> {
			NavFacet<Tag> s = convertTagToNavFacet(t);
			s.setFacetProductCount(this.getCountForTag(locale, currency, categoryDesc, lc, lb, t));
			return s;
		}).collect(Collectors.toList()).stream()
			.filter(t -> t.getFacetProductCount() > 0)
			.sorted(Comparator.comparing(NavFacet::getFacetProductCount
					, (s1, s2) -> {
			            return s2.compareTo(s1);
			        }))
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
//	
//	private Long getCountForCategory(String locale, String currency, String categoryDesc, Category category, List<Brand> brands, List<Tag> tags) {
//		List<Category> categories = new ArrayList<Category>();
//		categories.add(category);
//		
//		return productService.getCount(
//		CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
//		categoryDesc, 
//		locale, 
//		currency, 
//		ProductVars.ACTIVE_SKU_CODE,
//		categories, 
//		brands, 
//		tags
//		);
//	}

	@Override
    public NavFacet<Category> convertCatToNavFacet(final Category c) {
    	final NavFacet<Category> s = new NavFacet<Category>();
    	s.setFacetClassName(c.getClass().getSimpleName());
    	s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
    	s.setFacetId(calcFacetId(s.getFacetClassName(), c.getCategoryCode().toString()));
    	
    	System.out.println(c.getClass().getSimpleName());
    
    	if(c.getParentCode() != null) {
    		s.setFacetParentId(calcFacetId(s.getFacetClassName(), c.getParentCode().toString()));
    	}
    	s.setFacetChildCount(c.getChildCategoryCount());
    	s.setFacetProductCount(c.getCount());
    	
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
