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
									cnf.setMaxMarkdownPrice(productService.getMaxPriceForCategory(c.getCategoryId(), currency));	
									cnf.setProductCount(productService.getCountForCategory(c.getCategoryId()));
									return cnf;
							}).collect(Collectors.toList()).stream()
							  //.filter(nf -> nf.getProductCount() > 0)
							  .collect(Collectors.toList()));
		nfr.setResult(nfc);
		return nfr;
		
	}
	
	
	@Override
	public NavFacetResult findAll(String locale, String currency, String category, NavFacetContainer selectedFacets) {
		List<Long> tIds = selectedFacets.getTags().stream().map(t -> t.getId()).collect(Collectors.toList());
		List<Long> bIds = selectedFacets.getBrands().stream().map(t -> t.getId()).collect(Collectors.toList());
		List<Long> cIds = selectedFacets.getCategories().stream().map(t -> t.getId()).collect(Collectors.toList());
		
		Long proudctCount = productService.getCount(
				CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
				category, 
				locale, 
				currency, 
				ProductVars.ACTIVE_SKU_CODE,
				
				//these are a bit risky
				new ArrayList<Long>(cIds), 
				new ArrayList<Long>(bIds), 
				new ArrayList<Long>(tIds)
		);
		
		NavFacetContainer nfc = new NavFacetContainer();
		NavFacetResult nfr = new NavFacetResult();
		
		List<Category> categories = categoryService.findAll(locale, category, bIds, tIds);
		List<Brand> brands = brandService.findAll(locale, currency, category, cIds, tIds);
		List<Tag> tags = tagService.findAll(locale, currency, category, cIds, bIds);
	
		 
		List<NavFacet<Category>> catBars = categories.stream().map(c -> {
			List<Long> categoryIds = new ArrayList<Long>();
			categoryIds.add(c.getCategoryId());
			NavFacet<Category> s = convertCatToNavFacet(c);
			s.setProductCount(proudctCount);
			return s;
		}).collect(Collectors.toList()).stream()
			//.filter(c -> c.getProductCount() > 0)
			.collect(Collectors.toList());
	
		List<NavFacet<Brand>> brandBars = brands.stream().map(b -> {
			List<Long> brandIds = new ArrayList<Long>();
			brandIds.add(b.getBrandId());
			NavFacet<Brand> s = convertBrandToNavFacet(b);
			s.setProductCount(proudctCount);
			return s;
		}).collect(Collectors.toList()).stream()
			//.filter(c -> c.getProductCount() > 0)
			.collect(Collectors.toList());
		
		List<NavFacet<Tag>> tagBars = tags.stream().map(t -> {
			List<Long> tagIds = new ArrayList<Long>();
			tagIds.add(t.getTagId());
			NavFacet<Tag> s = convertTagToNavFacet(t);
			s.setProductCount(proudctCount);
			return s;
		}).collect(Collectors.toList()).stream()
			//.filter(t -> t.getProductCount() > 0)
			.collect(Collectors.toList());
		
		nfc.setBrands(brandBars);
		nfc.setCategories(catBars);
		nfc.setTags(tagBars);
		
		nfr.setResult(nfc);
		
		return nfr;
		
	}

    private NavFacet<Category> convertCatToNavFacet(final Category c) {
    	final NavFacet<Category> s = new NavFacet<Category>();
    	s.setFacetClassName(c.getClass().getSimpleName());
    	s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
    	s.setId(c.getCategoryId());
    	s.setFacetDisplayValue(c.getCategoryDesc());
    	s.setToken(c.getCategoryCode());
    	s.setPayload(c);
		return s;
    } 
	
	private NavFacet<Tag> convertTagToNavFacet(Tag t) {
		NavFacet<Tag> s = new NavFacet<Tag>();
		s.setFacetClassName(t.getClass().getSimpleName());
		s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
		s.setId(t.getTagId());
		s.setFacetDisplayValue(t.getTagDesc());
		s.setToken(t.getTagCode());
		s.setPayload(t);
		return s;
	}
	
    private NavFacet<Brand> convertBrandToNavFacet(final Brand b) {
    	final NavFacet<Brand> s = new NavFacet<Brand>();
    	s.setFacetClassName(b.getClass().getSimpleName());
    	s.setFacetType(ProductVars.FACET_TYPE_DISCRETE);
    	s.setId(b.getBrandId());
    	s.setFacetDisplayValue(b.getBrandDesc());
    	s.setToken(b.getBrandCode());
    	s.setPayload(b);
		return s;
    }


}
