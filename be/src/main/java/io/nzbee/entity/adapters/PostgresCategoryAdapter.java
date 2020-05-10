package io.nzbee.entity.adapters;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.entity.category.ICategoryMapper;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.brand.ICategoryBrandService;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.ICategoryProductService;
import io.nzbee.exceptions.category.CategoryNotFoundException;


@Component
public class PostgresCategoryAdapter implements ICategoryPortService {

	@Autowired 
	private ICategoryService categoryService;
	
	@Autowired 
	private ICategoryProductService categoryProductService;
	
	@Autowired 
	private ICategoryBrandService categoryBrandService;
	
	@Autowired
	@Qualifier(value = "categoryMapper")
	private ICategoryMapper categoryMapper;

	@Override
	public Set<Category> findAll(String locale, String currency, Set<String> codes) {
		return categoryService.findAll(locale, currency, codes)
				.stream().map(c -> (Category) categoryMapper.entityToDo(c, locale, currency)).collect(Collectors.toSet());
	}
	
	@Override
	public Category findByCode(String locale, String currency, String code) {
		io.nzbee.entity.category.Category cp = categoryService.findByCode(locale, currency, code)
				.orElseThrow(() -> new CategoryNotFoundException("Primary category for code " + code + " not found!"));
		return categoryMapper.entityToDo(cp, locale, currency);
	}	
	
	@Override
	public Category findByDesc(String locale, String currency, String desc) {
		io.nzbee.entity.category.Category cp = categoryService.findByDesc(locale, currency, desc)
				.orElseThrow(() -> new CategoryNotFoundException("Primary category for desc " + desc + " not found!"));
		return categoryMapper.entityToDo(cp, locale, currency);
	}
	
	@Override
	public Set<ProductCategory> findAllByProductCode(String locale, String currency, String productCode) {
		return categoryProductService.findAllByProductCode(locale, currency, productCode)
				.stream().map(c -> (ProductCategory) categoryMapper.entityToDo(c, locale, currency)).collect(Collectors.toSet());
	}
	
	@Override
	public ProductCategory findPrimaryByProductCode(String locale, String currency, String productCode) {
		CategoryProduct cp = categoryProductService.findPrimaryByProductCode(locale, currency, productCode)
				.orElseThrow(() -> new CategoryNotFoundException("Primary category for product code " + productCode + " not found!"));
		return (ProductCategory) categoryMapper.entityToDo(cp, locale, currency);		
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
				.stream().map(c -> categoryMapper.entityToDo(c, locale, currency)).collect(Collectors.toSet());
	}
	
	@Override
	public Set<ProductCategory> findAllProductCategories(String locale, String currency) {
		return categoryService.findAll(locale, currency, CategoryProduct.class)
				.stream().map(c -> (ProductCategory) categoryMapper.entityToDo(c, locale, currency)).collect(Collectors.toSet());
	}
	
	@Override
	public Set<BrandCategory> findAllBrandCategories(String locale, String currency) {
		return categoryService.findAll(locale, currency, CategoryBrand.class)
				.stream().map(c -> (BrandCategory) categoryMapper.entityToDo(c, locale, currency)).collect(Collectors.toSet());
	}
	
	@Override
	public void save(Category domainObject) {
		
		if (domainObject instanceof ProductCategory) {
			ProductCategory pc = (ProductCategory) domainObject;
			
			CategoryProduct cp = new CategoryProduct();
			cp.setCategoryCode(pc.getCategoryCode());
			cp.setLocale(pc.getLocale());
			cp.setCurrency(pc.getCurrency());
			cp.setCategoryLevel(pc.getCategoryLevel());
			cp.setObjectCount(pc.getCount());
			
			CategoryAttribute ca = new CategoryAttribute();
			ca.setCategoryDesc(pc.getCategoryDesc());
			ca.setLclCd(pc.getLocale());
			
			ca.setCategory(cp);
			cp.addAttribute(ca);
			
			categoryProductService.save(cp);
		}
		
		if(domainObject instanceof BrandCategory) {
			BrandCategory bc = (BrandCategory) domainObject;
			
			CategoryBrand cb = new CategoryBrand();
			cb.setCategoryCode(bc.getCategoryCode());
			cb.setLocale(bc.getLocale());
			cb.setCurrency(bc.getCurrency());
			cb.setCategoryLevel(bc.getCategoryLevel());
			cb.setObjectCount(bc.getCount());
			
			CategoryAttribute ca = new CategoryAttribute();
			ca.setCategoryDesc(bc.getCategoryDesc());
			ca.setLclCd(bc.getLocale());
			
			ca.setCategory(cb);
			cb.addAttribute(ca);
			
			categoryBrandService.save(cb);
		}
	
	}

	@Override
	public void update(Category domainObject) {
		// TODO Auto-generated method stub
		
	}

}
