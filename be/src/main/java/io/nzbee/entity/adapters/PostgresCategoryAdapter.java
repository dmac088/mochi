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
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.attribute.ICategoryAttributeService;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.brand.ICategoryBrandService;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.ICategoryProductService;
import io.nzbee.exceptions.category.CategoryNotFoundException;

@Component
public class PostgresCategoryAdapter implements ICategoryPortService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private ICategoryService categoryService;
	
	@Autowired 
	private ICategoryProductService categoryProductService;
	
	@Autowired
	private ICategoryAttributeService categoryAttributeService;
	
	@Autowired 
	private ICategoryBrandService categoryBrandService;
	
	@Autowired
	private ICategoryMapper categoryMapper;

	@Override
	public Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes) {
		return categoryService.getMaxPrice(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes);
	}
	
	@Override
	public Set<Category> findAll(String locale, String currency, Set<String> codes) {
		return categoryService.findAll(locale, currency, codes)
				.stream().map(c -> (Category) categoryMapper.entityToDo(c)).collect(Collectors.toSet());
	}

	@Override
	public Set<Category> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice) {
		LOGGER.debug("call PostgresCategoryAdapter.findAll parameters : locale = {}, currency = {}, categoryCode = {}, category codes = {}, brand codes = {}, tag codes = {}, max price = ", locale, currency, categoryCode, brandCodes, tagCodes, maxPrice);
		return categoryService.findAll(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes, maxPrice)
				.stream().map(c -> (ProductCategory) categoryMapper.entityToDo(c)).collect(Collectors.toSet());
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
			
			System.out.println("category code = " + domainObject.getCategoryCode());
			System.out.println("locale = " + domainObject.getLocale());
			
			Optional<io.nzbee.entity.category.Category> oc = 
					categoryService.findByCode(domainObject.getLocale(), 
											   domainObject.getCurrency(), 
											   domainObject.getCategoryCode());

			CategoryProduct cp = (oc.isPresent()) 
								 ? (CategoryProduct) oc.get()
								 : new CategoryProduct();
			
			CategoryAttribute ca = (oc.isPresent()) 
								 ? cp.getAttributes().stream().filter(a -> a.getLclCd().equals(domainObject.getLocale())).findFirst().get()
								 : new CategoryAttribute();
			
			cp.setCategoryCode(domainObject.getCategoryCode());
			cp.setCategoryLevel(((ProductCategory) domainObject).getCategoryLevel());
			
			ca.setCategoryDesc(domainObject.getCategoryDesc());
			ca.setLclCd(domainObject.getLocale());
			ca.setCategory(cp);
			cp.addCategoryAttribute(ca);
			
			categoryProductService.save(cp);
		}
		
		if(domainObject instanceof BrandCategory) {			
			
			Optional<io.nzbee.entity.category.Category> oc = 
					categoryService.findByCode(domainObject.getLocale(), 
											   domainObject.getCurrency(), 
											   domainObject.getCategoryCode());
			
			CategoryBrand cb = (oc.isPresent()) 
					 ? (CategoryBrand) oc.get()
					 : new CategoryBrand();

			CategoryAttribute ca = (oc.isPresent()) 
					 ? cb.getAttributes().stream().filter(a -> a.getLclCd().equals(domainObject.getLocale())).findFirst().get()
					 : new CategoryAttribute();
			
			ca.setCategoryDesc(domainObject.getCategoryDesc());
			ca.setLclCd(domainObject.getLocale());
			ca.setCategory(cb);
			
			cb.setCategoryCode(domainObject.getCategoryCode());
			
			cb.addCategoryAttribute(ca);
			cb.setCategoryAttribute(ca);
			
			categoryBrandService.save(cb);
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
