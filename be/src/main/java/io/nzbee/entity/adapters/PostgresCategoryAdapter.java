package io.nzbee.entity.adapters;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.LayoutCategory;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.entity.category.ICategoryMapper;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.category.brand.ICategoryBrandMapper;
import io.nzbee.entity.category.brand.ICategoryBrandService;
import io.nzbee.entity.category.layout.CategoryLayout;
import io.nzbee.entity.category.layout.ICategoryLayoutMapper;
import io.nzbee.entity.category.layout.ICategoryLayoutService;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.product.ICategoryProductMapper;
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
	private ICategoryLayoutService categoryLayoutService;
	
	@Autowired 
	private ICategoryProductMapper categoryProductMapper;
	
	@Autowired 
	private ICategoryBrandMapper categoryBrandMapper;
	
	@Autowired 
	private ICategoryLayoutMapper categoryLayoutMapper;
	
	@Autowired
	private ICategoryMapper categoryMapper;

	@Override
	public Set<Category> findAll(String locale, String currency, Set<String> codes) {
		return categoryService.findAll(locale, currency, codes)
				.stream().map(c -> (Category) categoryMapper.entityToDo(c)).collect(Collectors.toSet());
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
	public Set<LayoutCategory> findAllLayoutCategories(String locale, String currency) {
		return categoryService.findAll(locale, currency, CategoryLayout.class)
				.stream().map(c -> (LayoutCategory) categoryMapper.entityToDo(c)).collect(Collectors.toSet());
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
		if(domainObject instanceof LayoutCategory) {
			categoryLayoutService.save(categoryLayoutMapper.doToEntity((LayoutCategory) domainObject));
		}
	
	}

	@Override
	public void update(Category domainObject) {
		// TODO Auto-generated method stub
		
	}

	

}
