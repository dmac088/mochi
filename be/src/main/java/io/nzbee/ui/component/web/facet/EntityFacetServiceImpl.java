package io.nzbee.ui.component.web.facet;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.nzbee.domain.IDomainObject;
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
public class EntityFacetServiceImpl extends UIService implements IEntityFacetService {

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
	public EntityFacetResult findAll(String locale, String currency) {
		EntityFacetContainer nfc = new EntityFacetContainer();
		EntityFacetResult nfr = new EntityFacetResult();
		
		nfc.getFacets().addAll(	categoryService.findAll(locale, currency).stream().map(c -> {
									EntityFacet<Category> cnf = c.toFacet();
									return cnf;
							})
							.collect(Collectors.toList()));
		
		nfr.setResult(nfc);
		return nfr;
		
	}
	
	@Override
	public EntityFacetResult findAllBrands(String locale, String category) {
		List<Brand> brands = brandService.findAll(category, locale);
		
		EntityFacetContainer nfc = new EntityFacetContainer();
		EntityFacetResult nfr = new EntityFacetResult();
		
		List<EntityFacet<Brand>> brandBars = brands.stream().map(b -> {
			EntityFacet<Brand> s = convertBrandToNavFacet(b);
			return s;
		}).collect(Collectors.toList());
		
		nfc.getFacets().addAll(brandBars);
		
		nfr.setResult(nfc);
		
		return nfr;
	}
	
	@Override
	public EntityFacetResult findAll(String locale, String currency, String categoryDesc, EntityFacetContainer selectedFacets) {
		List<IDomainObject> lt = selectedFacets.getFacets().stream().map(t -> (IDomainObject) t.getEntity()).collect(Collectors.toList());
		
		EntityFacetContainer nfc = new EntityFacetContainer();
		EntityFacetResult nfr = new EntityFacetResult();
		
		List<Category> categories = categoryService.findAll(locale, currency, categoryDesc, lb, lt);
		List<Brand> brands = brandService.findAll(locale, currency, categoryDesc, lc, lt);
		List<Tag> tags = tagService.findAll(locale, currency, categoryDesc, lc, lb);

		List<EntityFacet<Category>> catBars = categories.stream().map(c -> {
			EntityFacet<Category> s = convertCatToNavFacet(c);
			return s;
		}).collect(Collectors.toList());
	
		List<EntityFacet<Brand>> brandBars = brands.stream().map(b -> {
			EntityFacet<Brand> s = convertBrandToNavFacet(b);
			return s;
		}).collect(Collectors.toList());
		
		List<EntityFacet<Tag>> tagBars = tags.stream().map(t -> {
			EntityFacet<Tag> s = convertTagToNavFacet(t);
			return s;
		}).collect(Collectors.toList());
		
		nfc.getFacets().addAll(brandBars);
		nfc.getFacets().addAll(catBars);
		nfc.getFacets().addAll(tagBars);
		
		nfr.setResult(nfc);
		
		return nfr;
		
	}
	
	@Override
    public EntityFacet<Category> convertCatToNavFacet(final Category c) {
    	final EntityFacet<Category> s = new EntityFacet<Category>();
    	s.setFacetClassName(c.getClass().getSimpleName());
    	s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
    	s.setFacetId(calcFacetId(s.getFacetClassName(), c.getCategoryCode().toString()));
    	s.setFacetDisplayValue(c.getCategoryDesc());
    	s.setToken(calcToken(s.getFacetClassName(), c.getCategoryCode()));
    	s.setPayload(c);
		return s;
    } 
	
    @Override
	public EntityFacet<Tag> convertTagToNavFacet(Tag t) {
		EntityFacet<Tag> s = new EntityFacet<Tag>();
		s.setFacetClassName(t.getClass().getSimpleName());
		s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
		s.setFacetId(calcFacetId(s.getFacetClassName(), t.getTagCode()));
		s.setFacetDisplayValue(t.getTagDesc());
		s.setToken(calcToken(s.getFacetClassName(), t.getTagCode()));
		s.setPayload(t);
		return s;
	}
	
    @Override
    public EntityFacet<Brand> convertBrandToNavFacet(final Brand b) {
    	final EntityFacet<Brand> s = new EntityFacet<Brand>();
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

	@Override
	public EntityFacet toEntityFacet(IDomainObject dO) {
		// TODO Auto-generated method stub
		return null;
	}

}
