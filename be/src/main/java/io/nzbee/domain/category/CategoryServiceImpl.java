package io.nzbee.domain.category;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import io.nzbee.domain.ports.ICategoryPortService;

public class CategoryServiceImpl implements ICategoryService {
    
    @Autowired
    private ICategoryPortService categoryService;
    
    @Override
    @Transactional(readOnly=true)
	public Set<Category> findAll(String locale) {
    	return categoryService.findAll(locale);
	}
    
    @Override
	public Set<Category> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes, Double maxPrice) {
		return categoryService.findAll(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes, maxPrice);
	}
    
    @Override
	public Double getMaxPrice(String locale, String currency, String categoryCode, Set<String> categoryCodes, Set<String> brandCodes, Set<String> tagCodes) {
		return categoryService.getMaxPrice(locale, currency, categoryCode, categoryCodes, brandCodes, tagCodes);
	}

	@Override
	@Transactional(readOnly=true)
	public Category findByCode(String locale, String code) {
		return categoryService.findByCode(locale, code);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Category findByDesc(String locale, String desc) {
		return categoryService.findByDesc(locale, desc);
	}
    
    @Override
    @Transactional(readOnly=true)
 	public Set<Category> findByParent(String locale, String parentCategoryCode) {
    	return categoryService.findByParent(parentCategoryCode, locale);
 	}
    
    @Override
    @Transactional(readOnly=true)
  	public Set<Category> findAllForLevel(String locale, Long level) {
     	return categoryService.findAllForLevel(locale, level);
  	}	
    
    @Override
    @Transactional(readOnly=true)
  	public Set<ProductCategory> findAllByProductCode(String locale, String code) {
     	return categoryService.findAllByProductCode(locale, code);
  	}	
    
    @Override
	@Transactional(readOnly=true)
	public Set<Category> findAll(String locale, Set<String> codes) {
		return categoryService.findAll(locale, codes);
	}
    
    @Override
	public Set<ProductCategory> findAllProductCategories(String locale) {
		return categoryService.findAllProductCategories(locale);
	}

	@Override
	public Set<BrandCategory> findAllBrandCategories(String locale) {
		return categoryService.findAllBrandCategories(locale);
	}	

	@Override
	public void save(Category object) {
		categoryService.save(object);
	}

	@Override
	public void delete(Category object) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Category object) {
		// TODO Auto-generated method stub
	}

}