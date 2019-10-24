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
    			.stream().map(c -> entityToDTO(locale, currency ,c))
    			.collect(Collectors.toList());
	}	
    
    
    @Override
	public Optional<Category> findById(String locale, String currency, long Id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(entityToDTO(locale, currency, categoryService.findById(locale, currency, Id).get()));
	}


	@Override
	public Optional<Category> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(entityToDTO(locale, currency, categoryService.findByCode(locale, currency, code).get()));
	}
	


	@Override
	public Optional<Category> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(entityToDTO(locale, currency, categoryService.findByDesc(locale, currency, desc).get()));
	}

	
    @Override
 	@Transactional
 	//@Cacheable
 	public List<Category> findByParent(String locale, String currency, String parentCategoryCode) {
    	return categoryService.findByParent(locale, parentCategoryCode)
		    	.stream().map(c -> entityToDTO(locale, currency, c))
				.collect(Collectors.toList());
 	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public List<Category> findAllForLevel(String locale, String currency, Long level) {
     	return categoryService.findAllForLevel(locale, currency, level)
		    	.stream().map(c -> entityToDTO(locale, currency, c))
				.collect(Collectors.toList());
  	}	
    
	@Override
	public List<Category> findAll(String locale, String currency, List<String> categoryCodes) {
		// TODO Auto-generated method stub
		return categoryService.findAll(locale, currency, categoryCodes)
			   .stream().map(c -> entityToDTO(locale, currency, c))
			   .collect(Collectors.toList());
	}
    
    @Override
	@Transactional
	//@Cacheable
	public List<Category> findAll(String locale, String currency, String categoryDesc, List<Brand> brands, List<Tag> tags) {
    	return categoryService.findAll(
    			locale,
    			categoryDesc, 
    			brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()), 
    			tags.stream().map(t -> t.getTagCode()).collect(Collectors.toList()) 
    			)
    			.stream().map(c -> entityToDTO(locale, currency, c))
				.collect(Collectors.toList());    			
	}

	@Override
	public Category entityToDTO(String locale, String currency, Object entity) {
		// TODO Auto-generated method stub
		io.nzbee.entity.category.Category category = (io.nzbee.entity.category.Category) entity;
		
		Category categoryDTO = new Category();
				
		categoryDTO.setCategoryCode(category.getCategoryCode());
		//categoryDTO.setCategoryDesc(category.getCategoryAttribute().getCategoryDesc());
		categoryDTO.setCategoryLevel(category.getCategoryLevel());
		categoryDTO.setCategoryType(category.getCategoryType().getDesc());
		categoryDTO.setObjectCount(category.getObjectCount());
		categoryDTO.setParentCode(category.getParent().getCategoryCode());
		categoryDTO.setChildCategoryCount(category.getChildCount());					
		categoryDTO.setLayoutCodes(category.getCategoryLayouts());
		
		return categoryDTO;
	}


	@Override
	public Category doToDto(Object dO) {
		// TODO Auto-generated method stub
		return null;
	}
    
}