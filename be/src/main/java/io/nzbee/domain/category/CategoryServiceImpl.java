package io.nzbee.domain.category;

import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import io.nzbee.domain.ports.ICategoryPortService;

public class CategoryServiceImpl implements ICategoryService {
    
    @Autowired
    private ICategoryPortService categoryService;
    
    @Override
    @Transactional(readOnly=true)
	public Set<Category> findAll(String locale, String currency) {
    	return categoryService.findAll(locale, currency);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Category> findByCode(String locale, String currency, String code) {
		return categoryService.findByCode(locale, currency, code);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Category> findByDesc(String locale, String currency, String desc) {
		return categoryService.findByDesc(locale, currency, desc);
	}
    
    @Override
    @Transactional(readOnly=true)
 	public Set<Category> findByParent(String locale, String currency, String parentCategoryCode) {
    	return categoryService.findByParent(parentCategoryCode, currency, locale);
 	}
    
    @Override
    @Transactional(readOnly=true)
  	public Set<Category> findAllForLevel(String locale, String currency, Long level) {
     	return categoryService.findAllForLevel(locale, currency, level);
  	}	
    
    @Override
    @Transactional(readOnly=true)
  	public Set<ProductCategory> findAllByProductCode(String locale, String currency, String code) {
     	return categoryService.findAllByProductCode(locale, currency, code);
  	}	
    
    @Override
	@Transactional(readOnly=true)
	public Set<Category> findAll(String locale, String currency, Set<String> codes) {
		return categoryService.findAll(locale, currency, codes);
	}

	@Override
	public void save(Category object) {
		categoryService.save(object);
	}

	@Override
	public void delete(Category object) {
		// TODO Auto-generated method stub
		
	}	

}