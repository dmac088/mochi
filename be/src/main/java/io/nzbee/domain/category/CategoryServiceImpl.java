package io.nzbee.domain.category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.tag.Tag;
import io.nzbee.variables.CategoryVars;

@Service
@Transactional
@CacheConfig(cacheNames="categories")
public class CategoryServiceImpl implements ICategoryService {
    
   
    @Autowired
    @Qualifier("categoryEntityService")
    private io.nzbee.entity.category.ICategoryService categoryService;
     
    @Override
	@Transactional
	//@Cacheable
	public List<Category> findAll(String locale, String currency) {
    	return  categoryService.findAll(locale, currency)
    			.stream().map(c -> convertCategoryDtoToCategoryDO(c))
    			.collect(Collectors.toList());
	}

	@Override
	public Optional<Category> findOneByCode(String locale, String categoryCode) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(convertCategoryDtoToCategoryDO(categoryService.findByCategoryCode(categoryCode, locale).get()));
	}
	

	@Override
	public Optional<Category> findParent(String locale, String categoryCode) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(convertCategoryDtoToCategoryDO(categoryService.findByCategoryCode(locale, categoryService.findByCategoryCode(categoryCode, locale).get().getParent().getCategoryCode()).get()));
	}
    
    @Override
 	@Transactional
 	//@Cacheable
 	public List<Category> findByParent(String locale, String parentCategoryCode) {
    	return categoryService.findByParent(parentCategoryCode, locale)
							  .stream().map(c -> convertCategoryDtoToCategoryDO(c))
							  .collect(Collectors.toList());
 	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public List<Category> findAllForLevel(Long level, String locale) {
     	return categoryService.findAllForLevel(level, locale)
     						  .stream().map(c -> convertCategoryDtoToCategoryDO(c))
     						  .collect(Collectors.toList());
  	}	
    
  
    @Override
  	@Transactional
  	//@Cacheable
  	public Optional<Category> findOne(final String locale, final String categoryCode) {
    	return Optional.ofNullable(convertCategoryDtoToCategoryDO(categoryService.findByCategoryCode(categoryCode, locale).get()));
  	}
    
    @Override
	@Transactional
	//@Cacheable
	public Optional<Category> findOneByDesc(String locale, String categoryDesc) {
    	return Optional.ofNullable(convertCategoryDtoToCategoryDO(categoryService.findByCategoryDesc(locale, categoryDesc).get()));
   
	}
    
    @Override
	@Transactional
	//@Cacheable
	public List<Category> findAll(String locale, String categoryDesc, List<Brand> brands, List<Tag> tags) {
    	return categoryService.findAll(
    			 categoryDesc, 
				 brands.stream().map(b -> b.getBrandDesc()).collect(Collectors.toList()),  
				 tags.stream().map(t -> t.getTagDesc()).collect(Collectors.toList()),
				 locale)
    			.stream().map(c -> convertCategoryDtoToCategoryDO(c))
    			.collect(Collectors.toList());
	}


	@Override
	public Category convertCategoryDtoToCategoryDO(io.nzbee.entity.category.Category category) {
		// TODO Auto-generated method stub
		
		Category categoryDO = category.getCategoryType().getCode().equals(CategoryVars.CATEGORY_TYPE_CODE_PRODUCT) 
							? new ProductCategory()
							: new BrandCategory();
		
		categoryDO.setCategoryCode(category.getCategoryCode());
		//categoryDO.setCategoryDesc(category.getCategoryAttribute().getCategoryDesc());
		categoryDO.setCategoryLevel(category.getCategoryLevel());
		categoryDO.setCategoryType(category.getCategoryType().getCode());
		categoryDO.setCount(category.getObjectCount());
							
		return categoryDO;
	}

}