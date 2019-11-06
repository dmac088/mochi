package io.nzbee.ui.component.web.facet;

import java.util.List;
import java.util.Set;
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
import io.nzbee.ui.component.web.facet.navigation.EntityFacet;
import io.nzbee.ui.component.web.facet.search.SearchFacet;
import io.nzbee.ui.component.web.generic.UIService;

@Service(value = "searchFacetService")
@Transactional
//@CacheConfig(cacheNames="products")
public class FacetServiceImpl extends UIService implements IFacetService {

	@Autowired
	@Qualifier("tagDomainService")
	private ITagService tagDomainService;
	
	@Autowired
	@Qualifier("categoryDomainService")
	private ICategoryService categoryDomainService;
	
	@Autowired
	@Qualifier("categoryDtoService")
	private io.nzbee.dto.category.ICategoryService categoryDTOService;

	@Autowired
	@Qualifier("brandDomainService")
	private IBrandService brandDomainService;

	@Autowired
	@Qualifier("productDomainService")
	private IProductService productService;

	@Override
	public FacetResult findAll(String locale, String currency) {
		FacetContainer nfc = new FacetContainer();
		FacetResult nfr = new FacetResult();
		
		nfc.getFacets().addAll(	categoryDomainService.findAll(locale, currency).stream().map(c -> {
									IFacet cnf = new EntityFacet(c);
									return cnf;
							})
							.collect(Collectors.toList()));
		
		nfr.setResult(nfc);
		return nfr;
		
	}
	
	@Override
	public FacetResult findAllBrands(String locale, String category) {
		Set<Brand> brands = brandDomainService.findAll(category, locale);
		
		FacetContainer nfc = new FacetContainer();
		FacetResult nfr = new FacetResult();
		
		nfc.getFacets().addAll(	brands.stream().map(b -> {
			IFacet brand = new EntityFacet(b);
			return brand;
		}).collect(Collectors.toList()));
		
		nfr.setResult(nfc);

		return nfr;
	}
	
	@Override
	public FacetResult findAll(String locale, String currency, String categoryDesc, FacetContainer selectedFacets) {
		//List<IDomainObject<?>> lt = selectedFacets.getFacets().stream().map(t -> (IDomainObject<?>) t.getEntity()).collect(Collectors.toList());
		
		FacetContainer nfc = new FacetContainer();
		FacetResult nfr = new FacetResult();
		
		List<EntityFacet> facets = categoryDomainService.findAll(
										locale, 
										currency, 
										categoryDesc, 
										selectedFacets.getFacets()
										.stream().filter(o -> !o.getPayload().getClass().equals(Category.class))
										
										.map(c -> (IDomainObject) c.getPayload()).collect(Collectors.toList()))
									.stream().map(dO -> new EntityFacet(dO))
									.collect(Collectors.toList()); 
									
		
//		List<Brand> brands = brandDomainService.findAll(locale, currency, categoryDesc, selectedFacets.getFacets());
//		List<Tag> tags = tagDomainService.findAll(locale, currency, categoryDesc, selectedFacets.getFacets());
//
//		List<SearchFacet<Category>> catBars = categories.stream().map(c -> {
//			SearchFacet<Category> s = convertCatToNavFacet(c);
//			return s;
//		}).collect(Collectors.toList());
//	
//		List<SearchFacet<Brand>> brandBars = brands.stream().map(b -> {
//			SearchFacet<Brand> s = convertBrandToNavFacet(b);
//			return s;
//		}).collect(Collectors.toList());
//		
//		List<SearchFacet<Tag>> tagBars = tags.stream().map(t -> {
//			SearchFacet<Tag> s = convertTagToNavFacet(t);
//			return s;
//		}).collect(Collectors.toList());
		
		//nfc.getFacets().addAll(brandBars);
		nfc.getFacets().addAll(facets);
		//nfc.getFacets().addAll(tagBars);
		
		nfr.setResult(nfc);
		
		return nfr;
		
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
	public SearchFacet toEntityFacet(IDomainObject dO) {
		// TODO Auto-generated method stub
		return null;
	}

}
