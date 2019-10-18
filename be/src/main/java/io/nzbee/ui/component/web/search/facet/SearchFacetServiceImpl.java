package io.nzbee.ui.component.web.search.facet;

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

@Service(value = "searchFacetService")
@Transactional
//@CacheConfig(cacheNames="products")
public class SearchFacetServiceImpl extends UIService implements ISearchFacetService {

	@Autowired
	@Qualifier("tagDomainService")
	private ITagService tagDomainService;
	
	@Autowired
	@Qualifier("categoryDomainService")
	private ICategoryService categoryDomainService;
	
	@Autowired
	@Qualifier("categoryDTOService")
	private io.nzbee.dto.category.ICategoryService categoryDTOService;

	@Autowired
	@Qualifier("brandDomainService")
	private IBrandService brandDomainService;

	@Autowired
	@Qualifier("productDomainService")
	private IProductService productService;

	@Override
	public SearchFacetResult findAll(String locale, String currency) {
//		SearchFacetContainer nfc = new SearchFacetContainer();
//		SearchFacetResult nfr = new SearchFacetResult();
//		
//		nfc.getFacets().addAll(	categoryDomainService.findAll(locale, currency).stream().map(c -> {
//									SearchFacet<Category> cnf = c.toFacet();
//									return cnf;
//							})
//							.collect(Collectors.toList()));
//		
//		nfr.setResult(nfc);
//		return nfr;
		return null;
	}
	
	@Override
	public SearchFacetResult findAllBrands(String locale, String category) {
//		List<Brand> brands = brandDomainService.findAll(category, locale);
//		
//		SearchFacetContainer nfc = new SearchFacetContainer();
//		SearchFacetResult nfr = new SearchFacetResult();
//		
//		List<SearchFacet<Brand>> brandBars = brands.stream().map(b -> {
//			SearchFacet<Brand> s = convertBrandToNavFacet(b);
//			return s;
//		}).collect(Collectors.toList());
//		
//		nfc.getFacets().addAll(brandBars);
//		
//		nfr.setResult(nfc);
//		
//		return nfr;
		return null;
	}
	
	@Override
	public SearchFacetResult findAll(String locale, String currency, String categoryDesc, SearchFacetContainer selectedFacets) {
		//List<IDomainObject<?>> lt = selectedFacets.getFacets().stream().map(t -> (IDomainObject<?>) t.getEntity()).collect(Collectors.toList());
		
		SearchFacetContainer nfc = new SearchFacetContainer();
		SearchFacetResult nfr = new SearchFacetResult();
		
		List<EntityFacet> facets = categoryDomainService.findAll(
										locale, 
										currency, 
										categoryDesc, 
										selectedFacets.getFacets()
										.stream().filter(o -> !o.getEntity().getClass().equals(Category.class))
										.map(c -> (IDomainObject) c.getEntity()).collect(Collectors.toList()))
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
