package io.nzbee.entity.adapters;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.entity.category.ICategoryMapper;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.brand.ICategoryBrandService;
import io.nzbee.entity.category.product.ICategoryProductService;

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
	public Set<ProductCategory> findAllByProductCode(String locale, String currency, String productCode) {
		return categoryProductService.findAllByProductCode(locale, currency, productCode)
				.stream().map(c -> (ProductCategory) categoryMapper.entityToDo(c, locale, currency)).collect(Collectors.toSet());
	}
	
	@Override
	public Optional<Category> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
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
	public Category findByCode(String locale, String currency, String code) {
		return categoryMapper.entityToDo(categoryService.findByCode(locale, currency, code).get(), locale, currency);
	}	
	
	@Override
	public Category findByDesc(String locale, String currency, String desc) {
		return categoryMapper.entityToDo(categoryService.findByDesc(locale, currency, desc).get(), locale, currency);
	}

	@Override
	public void save(Category domainObject) {
		if (domainObject instanceof ProductCategory) {
			categoryProductService.findByCode(	domainObject.getLocale(),
												domainObject.getCurrency(),
												domainObject.getCategoryCode());
		}
		categoryBrandService.findByCode(	domainObject.getLocale(),
											domainObject.getCurrency(),
											domainObject.getCategoryCode());
	}


}
