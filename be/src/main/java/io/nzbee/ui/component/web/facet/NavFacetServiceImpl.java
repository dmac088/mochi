package io.nzbee.ui.component.web.facet;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.tag.ITagService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.ui.component.web.generic.UIService;
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
		
		nfc.getFacets().addAll(	categoryService.findAll(locale, currency).stream().map(c -> {
									NavFacet<Category> cnf = this.convertCatToNavFacet(c);
									return cnf;
							})
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
		
		List<Category> categories = categoryService.findAll(locale, currency, categoryDesc, lb, lt);
		List<Brand> brands = brandService.findAll(locale, currency, categoryDesc, lc, lt);
		List<Tag> tags = tagService.findAll(locale, currency, categoryDesc, lc, lb);

		List<NavFacet<Category>> catBars = categories.stream().map(c -> {
			NavFacet<Category> s = convertCatToNavFacet(c);
			return s;
		}).collect(Collectors.toList());
	
		List<NavFacet<Brand>> brandBars = brands.stream().map(b -> {
			NavFacet<Brand> s = convertBrandToNavFacet(b);
			return s;
		}).collect(Collectors.toList());
		
		List<NavFacet<Tag>> tagBars = tags.stream().map(t -> {
			NavFacet<Tag> s = convertTagToNavFacet(t);
			return s;
		}).collect(Collectors.toList());
		
		nfc.getFacets().addAll(brandBars);
		nfc.getFacets().addAll(catBars);
		nfc.getFacets().addAll(tagBars);
		
		nfr.setResult(nfc);
		
		return nfr;
		
	}
	
	@Override
    public NavFacet<Category> convertCatToNavFacet(final Category c) {
    	final NavFacet<Category> s = new NavFacet<Category>();
    	s.setFacetClassName(c.getClass().getSimpleName());
    	s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
    	s.setFacetId(calcFacetId(s.getFacetClassName(), c.getCategoryCode().toString()));
    	s.setFacetDisplayValue(c.getCategoryDesc());
    	s.setToken(calcToken(s.getFacetClassName(), c.getCategoryCode()));
    	s.setPayload(c);
		return s;
    } 
	
    @Override
	public NavFacet<Tag> convertTagToNavFacet(Tag t) {
		NavFacet<Tag> s = new NavFacet<Tag>();
		s.setFacetClassName(t.getClass().getSimpleName());
		s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
		s.setFacetId(calcFacetId(s.getFacetClassName(), t.getTagCode()));
		s.setFacetDisplayValue(t.getTagDesc());
		s.setToken(calcToken(s.getFacetClassName(), t.getTagCode()));
		s.setPayload(t);
		return s;
	}
	
    @Override
    public NavFacet<Brand> convertBrandToNavFacet(final Brand b) {
    	final NavFacet<Brand> s = new NavFacet<Brand>();
    	s.setFacetClassName(b.getClass().getSimpleName());
    	s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
    	s.setFacetId(calcFacetId(s.getFacetClassName(), b.getBrandCode()));
    	s.setFacetDisplayValue(b.getBrandDesc());
    	s.setToken(calcToken(s.getFacetClassName(), b.getBrandCode()));
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
