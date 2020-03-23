package io.nzbee.domain.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import  org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import io.nzbee.domain.IDomainObject;
import io.nzbee.domain.IFacetService;
import io.nzbee.exceptions.CategoryException;

@Service(value = "categoryDomainService")
@CacheConfig(cacheNames="categories")
public class CategoryServiceImpl implements ICategoryService, IFacetService {
    
   
    @Autowired
    @Qualifier("categoryDtoService")
    private io.nzbee.dto.category.ICategoryService categoryService;
    
     
    @Override
	@Transactional(readOnly = true)
	//@Cacheable
	public Set<Category> findAll(String locale, String currency) {
    	return  categoryService.findAll(locale, currency)
    			.stream().map(c -> dtoToDO(Optional.ofNullable(c)).get())
    			.collect(Collectors.toSet());
	}

	@Override
	public Category findById(String locale, String currency, Long Id) {
		// TODO Auto-generated method stub
		
		return dtoToDO(categoryService.findById(locale, currency, Id))
				.orElseThrow(() -> new CategoryException("Category with Id " + Id + " not found"));
	}

	@Override
	public Category findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return dtoToDO(categoryService.findByCode(locale, currency, code))
				.orElseThrow(() -> new CategoryException("Category with code " + code + " not found"));
	}
	
	@Override
	public Category findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return dtoToDO(categoryService.findByDesc(locale, currency, desc)).get();
	}
    
    @Override
 	@Transactional
 	//@Cacheable
 	public Set<Category> findByParent(String locale, String currency, String parentCategoryCode) {
    	return categoryService.findByParent(parentCategoryCode, currency, locale)
							  .stream().map(c -> dtoToDO(Optional.ofNullable(c)).get())
							  .collect(Collectors.toSet());
 	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public Set<Category> findAllForLevel(String locale, String currency, Long level) {
     	return categoryService.findAllForLevel(locale, currency, level)
     						  .stream().map(c -> dtoToDO(Optional.ofNullable(c)).get())
     						  .collect(Collectors.toSet());
  	}	
    

    @Override
	public Set<Category> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return categoryService.findAll(locale, currency, codes)
							  .stream().map(c -> dtoToDO(Optional.ofNullable(c)).get())
							  .collect(Collectors.toSet());
	}
 
	@Override
	public Set<Category> findAll(String locale, String currency, String categoryDesc,
			List<IDomainObject> lDo) {
		// TODO Auto-generated method stub
		return null;
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
	public Optional<Category> dtoToDO(Optional<io.nzbee.dto.category.Category> c) {
		// TODO Auto-generated method stub
		if(c.isPresent()) {
			io.nzbee.dto.category.Category category = c.get();
			Category categoryDO = category.getType().equals(io.nzbee.dto.category.product.ProductCategory.class.getSimpleName()) 
			? new ProductCategory()
			: new BrandCategory();
	
			categoryDO.setCategoryCode(category.getCategoryCode());
			categoryDO.setCategoryDesc(category.getCategoryDesc());
			categoryDO.setCategoryLevel(category.getCategoryLevel());
			categoryDO.setCount(category.getObjectCount());
			if(category.getType().equals(io.nzbee.dto.category.product.ProductCategory.class.getSimpleName())) {
				((ProductCategory) categoryDO).setParentCode(((io.nzbee.dto.category.product.ProductCategory) category).getParentCode());
			}
			categoryDO.setChildCount(category.getChildCategoryCount());
			categoryDO.setLayoutCodes(category.getLayoutCodes());
			categoryDO.setLocale(category.getLocale());
			categoryDO.setCurrency(category.getCurrency());
			
			return Optional.ofNullable(categoryDO);
		}
		return Optional.empty();
		
	}

}