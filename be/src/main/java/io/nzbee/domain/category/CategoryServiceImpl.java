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
    @Qualifier("categoryDtoService")
    private io.nzbee.dto.category.ICategoryService categoryService;
    
    @Autowired
    @Qualifier("brandDtoService")
    private io.nzbee.dto.brand.IBrandService brandService;
    
    @Autowired
    @Qualifier("tagDtoService")
    private io.nzbee.dto.tag.ITagService tagService;
     
    @Override
	@Transactional
	//@Cacheable
	public List<Category> findAll(String locale, String currency) {
    	return  categoryService.findAll(locale, currency)
    			.stream().map(c -> dtoToDO(c))
    			.collect(Collectors.toList());
	}

	@Override
	public Optional<Category> findById(String locale, String currency, Long Id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(dtoToDO(categoryService.findById(locale, currency, Id).get()));
	}

	@Override
	public Optional<Category> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(dtoToDO(categoryService.findByCode(locale, currency, code).get()));
	}
	
	@Override
	public Optional<Category> findByDesc(String locale, String currency, String desc) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(dtoToDO(categoryService.findByDesc(locale, currency, desc).get()));
	}
    
    @Override
 	@Transactional
 	//@Cacheable
 	public List<Category> findByParent(String locale, String currency, String parentCategoryCode) {
    	return categoryService.findByParent(parentCategoryCode, currency, locale)
							  .stream().map(c -> dtoToDO(c))
							  .collect(Collectors.toList());
 	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public List<Category> findAllForLevel(String locale, String currency, Long level) {
     	return categoryService.findAllForLevel(locale, currency, level)
     						  .stream().map(c -> dtoToDO(c))
     						  .collect(Collectors.toList());
  	}	
    

    @Override
	public List<Category> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return categoryService.findAll(locale, currency, codes)
							  .stream().map(c -> dtoToDO(c))
							  .collect(Collectors.toList());
	}
 
    @Override
	@Transactional
	//@Cacheable
	public List<Category> findAll(String locale, String currency, String categoryDesc, List<Brand> brands, List<Tag> tags) {

    	return categoryService.findAll(
    			 locale,
    			 currency,
    			 categoryDesc,
    			// convert brand domain objects to brand DTOs
				 brands.stream().map(b -> brandService.doToDto(b)).collect(Collectors.toList()),  
				//convert tag domain objects to tag DTOs
				 tags.stream().map(t -> tagService.doToDto(t)).collect(Collectors.toList())  
				 )
    			.stream().map(c -> this.dtoToDO(c))
    			.collect(Collectors.toList());
	}


	@Override
	public Category dtoToDO(Object dto) {
		// TODO Auto-generated method stub
		io.nzbee.dto.category.Category category = (io.nzbee.dto.category.Category) dto;
		
		Category categoryDO = category.getCategoryType().equals(CategoryVars.CATEGORY_TYPE_CODE_PRODUCT) 
				? new ProductCategory()
				: new BrandCategory();

		categoryDO.setCategoryCode(category.getCategoryCode());
		categoryDO.setCategoryDesc(category.getCategoryDesc());
		categoryDO.setCategoryLevel(category.getCategoryLevel());
		categoryDO.setCategoryType(category.getCategoryType());
		categoryDO.setCount(category.getObjectCount());
		categoryDO.setParentCode(category.getParentCode());
		categoryDO.setChildCategoryCount(category.getChildCategoryCount());
		
		return categoryDO;
	}
	
}