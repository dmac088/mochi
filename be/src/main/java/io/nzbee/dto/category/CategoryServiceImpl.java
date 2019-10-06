package io.nzbee.dto.category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.brand.Brand;
import io.nzbee.dto.tag.Tag;

@Service(value = "categoryDtoService")
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
    	return categoryService.findAll(locale, currency)
    			.stream().map(c -> convertCategoryEntityToCategoryDTO(c))
    			.collect(Collectors.toList());
	}	


	@Override
	public Optional<Category> findOneByCode(String locale, String categoryCode) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(convertCategoryEntityToCategoryDTO(categoryService.findByCategoryCode(categoryCode, locale).get()));
			   
	}
	
    @Override
 	@Transactional
 	//@Cacheable
 	public List<Category> findByParent(String locale, String parentCategoryCode) {
    	return categoryService.findByParent(parentCategoryCode, locale)
		    	.stream().map(c -> convertCategoryEntityToCategoryDTO(c))
				.collect(Collectors.toList());
 	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public List<Category> findAllForLevel(String locale, Long level) {
     	return categoryService.findAllForLevel(locale, level)
		    	.stream().map(c -> convertCategoryEntityToCategoryDTO(c))
				.collect(Collectors.toList());
  	}	
    
    @Override
  	@Transactional
  	//@Cacheable
  	public Optional<Category> findOne(String locale, String categoryCode) {
    	return Optional.ofNullable(convertCategoryEntityToCategoryDTO(categoryService.findByCategoryCode(categoryCode, locale).get()));
    	
  	}
    
    @Override
	@Transactional
	//@Cacheable
	public Optional<Category> findOneByDesc(String locale, String categoryDesc) {
    	return Optional.ofNullable(convertCategoryEntityToCategoryDTO(categoryService.findByCategoryDesc(categoryDesc, locale).get()));
    	
   
	}
    
    @Override
	@Transactional
	//@Cacheable
	public List<Category> findAll(String locale, String categoryDesc, List<Brand> brands, List<Tag> tags) {
    	return categoryService.findAll(
    			locale,
    			categoryDesc, 
    			brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()), 
    			tags.stream().map(t -> t.getTagCode()).collect(Collectors.toList()) 
    			)
    			.stream().map(c -> convertCategoryEntityToCategoryDTO(c))
				.collect(Collectors.toList());    			
	}


	@Override
	public Optional<Category> findOne(String locale, Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Category convertCategoryEntityToCategoryDTO(io.nzbee.entity.category.Category category) {
		// TODO Auto-generated method stub
		
		Category categoryDTO = new Category();
		
		categoryDTO.setCategoryCode(category.getCategoryCode());
		categoryDTO.setCategoryDesc(category.getCategoryAttribute().getCategoryDesc());
		categoryDTO.setCategoryLevel(category.getCategoryLevel());
		categoryDTO.setCategoryType(category.getCategoryType().getCode());
		categoryDTO.setObjectCount(category.getObjectCount());
							
		return categoryDTO;
	}


    
}