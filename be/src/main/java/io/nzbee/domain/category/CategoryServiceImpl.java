package io.nzbee.domain.category;

import java.util.Set;
import  org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import io.nzbee.domain.IFacetService;

@Service(value = "categoryDomainService")
@CacheConfig(cacheNames="categories")
public class CategoryServiceImpl implements ICategoryService, IFacetService {
    
   
    @Autowired
    private io.nzbee.domain.ports.ICategoryPortService categoryService;
    
     
    @Override
	@Transactional(readOnly = true)
	public Set<Category> findAll(String locale, String currency) {
    	return categoryService.findAll(locale, currency);
	}

	@Override
	public Category findByCode(String locale, String currency, String code) {
		return categoryService.findByCode(locale, currency, code);
	}
	
	@Override
	public Category findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return categoryService.findByDesc(locale, currency, desc);
	}
    
    @Override
 	@Transactional
 	//@Cacheable
 	public Set<Category> findByParent(String locale, String currency, String parentCategoryCode) {
    	return categoryService.findByParent(parentCategoryCode, currency, locale);
 	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public Set<Category> findAllForLevel(String locale, String currency, Long level) {
     	return categoryService.findAllForLevel(locale, currency, level);
  	}	
    
	@Override
	public String tokenToCode(String token) {
		// TODO Auto-generated method stub
		return token.substring(token.lastIndexOf('/')+1,token.length());
	}

	@Override
	public String getFacetField() {
		// TODO Auto-generated method stub
		return "product.categories.categoryToken";
	}

	@Override
	public String getFacetCategory() {
		// TODO Auto-generated method stub
		return "category";
	}

	@Override
	public Set<Category> findAll(String lcl, String currency, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

}