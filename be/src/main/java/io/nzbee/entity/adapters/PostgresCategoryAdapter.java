package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.entity.category.ICategoryMapper;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.brand.ICategoryBrandMapper;
import io.nzbee.entity.category.brand.ICategoryBrandService;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.ICategoryProductMapper;
import io.nzbee.entity.category.product.ICategoryProductService;
import io.nzbee.exceptions.category.CategoryNotFoundException;
import io.nzbee.search.dto.facet.IFacet;

@Component
public class PostgresCategoryAdapter implements ICategoryPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private ICategoryService categoryService;
	
	@Autowired 
	private ICategoryProductService categoryProductService;
	
	@Autowired 
	private ICategoryBrandService categoryBrandService;
	
	@Autowired 
	private ICategoryProductMapper categoryProductMapper;
	
	@Autowired 
	private ICategoryBrandMapper categoryBrandMapper;
	
	@Autowired
	private ICategoryMapper categoryMapper;

	@Override
	public Double getMaxPrice(String locale, String currency, String categoryCode, Set<IFacet> selectedFacets) {
		//categoryService.
		return new Double(0);
	}
	
	@Override
	public Set<Category> findAll(String locale, String currency, Set<String> codes) {
		return categoryService.findAll(locale, currency, codes)
				.stream().map(c -> (Category) categoryMapper.entityToDo(c)).collect(Collectors.toSet());
	}

	@Override
	public Set<Category> findAll(String locale, String currency, String categoryCode, Set<IFacet> selectedFacets) {
		LOGGER.debug("call PostgresCategoryAdapter.findAll parameters : {}, {}, {}, {}", locale, currency, categoryCode, selectedFacets.size());
		
		Optional<String> oMaxPrice = selectedFacets.stream().filter(p -> p.getFacetingName().equals("maxPrice")).map(p -> p.getId()).findFirst();
    	Double maxPrice = null;
    	if(oMaxPrice.isPresent()) {
    		maxPrice = new Double(oMaxPrice.get());
    	}
    	
    	Set<Category> sc = categoryService.findAll(locale, currency, categoryCode,
    			selectedFacets.stream().filter(c -> c.getFacetingName().equals("category")).map(c -> c.getId())
				.collect(Collectors.toSet()),
				selectedFacets.stream().filter(c -> c.getFacetingName().equals("brand")).map(c -> c.getId())
						.collect(Collectors.toSet()),
				selectedFacets.stream().filter(c -> c.getFacetingName().equals("tag")).map(c -> c.getId())
						.collect(Collectors.toSet()),
				maxPrice).stream().map(c -> mapHelper(c)).collect(Collectors.toSet());
    	
    	return sc;
	}

	@Override
	public Category findByCode(String locale, String currency, String code) {
		io.nzbee.entity.category.Category cp = categoryService.findByCode(locale, currency, code)
				.orElseThrow(() -> new CategoryNotFoundException("Primary category for code " + code + " not found!"));
		return categoryMapper.entityToDo(cp);
	}	
	
	@Override
	public Category findByDesc(String locale, String currency, String desc) {
		io.nzbee.entity.category.Category cp = categoryService.findByDesc(locale, currency, desc)
				.orElseThrow(() -> new CategoryNotFoundException("Primary category for desc " + desc + " not found!"));
		return categoryMapper.entityToDo(cp);
	}
	
	@Override
	public Set<ProductCategory> findAllByProductCode(String locale, String currency, String productCode) {
		return categoryProductService.findAllByProductCode(locale, currency, productCode)
				.stream().map(c -> (ProductCategory) categoryMapper.entityToDo(c)).collect(Collectors.toSet());
	}
	
	@Override
	public ProductCategory findPrimaryByProductCode(String locale, String currency, String productCode) {
		CategoryProduct cp = categoryProductService.findPrimaryByProductCode(locale, currency, productCode)
				.orElseThrow(() -> new CategoryNotFoundException("Primary category for product code " + productCode + " not found!"));
		return (ProductCategory) categoryMapper.entityToDo(cp);		
	}

	@Override
	public Set<Category> findAllForLevel(String locale, String currency, Long level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Category> findByParent(String parentCategoryCode, String currency, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Category> findAll(String locale, String currency) {
		return categoryService.findAll(locale, currency)
				.stream().map(c -> categoryMapper.entityToDo(c)).collect(Collectors.toSet());
	}
	
	@Override
	public Set<ProductCategory> findAllProductCategories(String locale, String currency) {
		return categoryService.findAll(locale, currency, CategoryProduct.class)
				.stream().map(c -> (ProductCategory) categoryMapper.entityToDo(c)).collect(Collectors.toSet());
	}
	
	@Override
	public Set<BrandCategory> findAllBrandCategories(String locale, String currency) {
		return categoryService.findAll(locale, currency, CategoryBrand.class)
				.stream().map(c -> (BrandCategory) categoryMapper.entityToDo(c)).collect(Collectors.toSet());
	}
	
	@Override
	public void save(Category domainObject) {
		
		if (domainObject instanceof ProductCategory) {
			categoryProductService.save(categoryProductMapper.doToEntity((ProductCategory) domainObject));
		}
		
		if(domainObject instanceof BrandCategory) {			
			categoryBrandService.save(categoryBrandMapper.doToEntity((BrandCategory) domainObject));
		}
	
	}
	
	private Category mapHelper(io.nzbee.entity.category.Category c) {
		return categoryMapper.entityToDo(c);
	}

	@Override
	public void update(Category domainObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Category domainObject) {
		// TODO Auto-generated method stub
		
	}


}
