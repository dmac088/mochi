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
		List<Long> tIds = selectedFacets.getTags().stream().map(t -> Long.parseLong(t.getFacetId())).collect(Collectors.toList());
		List<Long> bIds = selectedFacets.getBrands().stream().map(t -> Long.parseLong(t.getFacetId())).collect(Collectors.toList());
		List<Long> cIds = selectedFacets.getCategories().stream().map(t -> Long.parseLong(t.getFacetId())).collect(Collectors.toList());
		
		NavFacetContainer nfc = new NavFacetContainer();
		NavFacetResult nfr = new NavFacetResult();
		
		List<Category> categories = categoryService.findAll(locale, categoryDesc, bIds, tIds);
		List<Brand> brands = brandService.findAll(locale, currency, categoryDesc, cIds, tIds);
		List<Tag> tags = tagService.findAll(locale, currency, categoryDesc, cIds, bIds);
	
		List<NavFacet<Category>> catBars = categories.stream().map(c -> {
			NavFacet<Category> s = convertCatToNavFacet(c);
			s.setFacetProductCount(this.getCountForCategory(locale, currency, categoryDesc, c, bIds, tIds));
			return s;
		}).collect(Collectors.toList()).stream()
			.filter(c -> c.getFacetProductCount() > 0)
			.collect(Collectors.toList());
	
		List<NavFacet<Brand>> brandBars = brands.stream().map(b -> {
			List<Long> brandIds = new ArrayList<Long>();
			NavFacet<Brand> s = convertBrandToNavFacet(b);
			s.setFacetProductCount(this.getCountForBrand(locale, currency, categoryDesc, cIds, b, tIds));
			return s;
		}).collect(Collectors.toList()).stream()
			.filter(c -> c.getFacetProductCount() > 0)
			.collect(Collectors.toList());
		
		List<NavFacet<Tag>> tagBars = tags.stream().map(t -> {
			List<Long> tagIds = new ArrayList<Long>();
			tagIds.add(t.getTagId());
			NavFacet<Tag> s = convertTagToNavFacet(t);
			s.setFacetProductCount(this.getCountForTag(locale, currency, categoryDesc, cIds, bIds, t.getTagId()));
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
	
	private Long getCountForBrand(String locale, String currency, String category, List<Long> cIds, Brand brand, List<Long> tIds) {
		List<Long> lbId = new ArrayList<Long>();
		return productService.getCount(
		CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
		category, 
		locale, 
		currency, 
		ProductVars.ACTIVE_SKU_CODE,
		new ArrayList<Long>(cIds), 
		lbId, 
		new ArrayList<Long>(tIds)
		);
	}
	
	
	private Long getCountForTag(String locale, String currency, String category, List<Long> cIds, List<Long> bIds, Long tagId) {
		List<Long> ltId = new ArrayList<Long>();
		ltId.add(tagId);
		return productService.getCount(
		CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
		category, 
		locale, 
		currency, 
		ProductVars.ACTIVE_SKU_CODE,
		new ArrayList<Long>(cIds), 
		new ArrayList<Long>(bIds), 
		ltId
		);
	}
	
	private Long getCountForCategory(String locale, String currency, String categoryDesc, Category category, List<Long> bIds, List<Long> tIds) {
		return productService.getCount(
		CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
		categoryDesc, 
		locale, 
		currency, 
		ProductVars.ACTIVE_SKU_CODE,
		category, 
		new ArrayList<Long>(bIds), 
		tIds
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
