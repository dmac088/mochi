package io.nzbee.dto.category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.tag.Tag;
import io.nzbee.variables.CategoryVars;

@Service(value = "categoryDtoService")
@Transactional
@CacheConfig(cacheNames="categories")
public class CategoryServiceImpl implements ICategoryService {
    
    @Autowired
    @Qualifier("categoryDtoDao")
    private io.nzbee.dto.category.ICategoryDao categoryDao;
    
    @Override
	@Transactional
	//@Cacheable
	public List<Category> findAll(String locale, String currency) {
    	return  categoryDao.findAll(locale, currency);
	}	


	@Override
	public Optional<Category> findOneByCode(String locale, String categoryCode) {
		// TODO Auto-generated method stub
		return categoryDao.findByCategoryCode(categoryCode, 
											  locale);
	}
	

	@Override
	public Optional<Category> findParent(String locale, String parentCategoryCode) {
		// TODO Auto-generated method stub
		return categoryDao.findByCategoryCode(parentCategoryCode, locale);
	}
    
    @Override
 	@Transactional
 	//@Cacheable
 	public List<Category> findByParent(String locale, String parentCategoryCode) {
    	return categoryDao.findByParent(CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, parentCategoryCode, locale);
 	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public List<Category> findAllForLevel(final String locale, final Long level) {
     	return categoryDao.findByLevel(CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, level, locale);
  	}	
    
    @Override
  	@Transactional
  	//@Cacheable
  	public Optional<Category> findOne(String locale, String categoryCode) {
    	return categoryDao.findByCategoryCode(categoryCode, locale);
  	}
    
    @Override
	@Transactional
	//@Cacheable
	public Optional<Category> findOneByDesc(String locale, String categoryDesc) {
    	return categoryDao.findByCategoryDesc(categoryDesc, locale);
   
	}
    
    @Override
	@Transactional
	//@Cacheable
	public List<Category> findAll(String locale, String categoryDesc, List<Brand> brands, List<Tag> tags) {
    	return categoryDao.findChildrenByCriteria(
				 categoryDesc, 
				 brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()),  
				 tags.stream().map(t -> t.getTagCode()).collect(Collectors.toList()),
				 locale);
	}


	@Override
	public Optional<Category> findOne(String locale, Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
    
}