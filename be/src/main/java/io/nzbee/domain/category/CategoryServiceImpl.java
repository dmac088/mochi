package io.nzbee.domain.category;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import io.nzbee.domain.IDomainObject;
import io.nzbee.domain.IFacetService;

@Service(value = "categoryDomainService")
@Transactional
@CacheConfig(cacheNames="categories")
public class CategoryServiceImpl implements ICategoryService, IFacetService {
    
   
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
	public Set<Category> findAll(String locale, String currency) {
    	return  categoryService.findAll(locale, currency)
    			.stream().map(c -> dtoToDO(c))
    			.collect(Collectors.toSet());
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
 	public Set<Category> findByParent(String locale, String currency, String parentCategoryCode) {
    	return categoryService.findByParent(parentCategoryCode, currency, locale)
							  .stream().map(c -> dtoToDO(c))
							  .collect(Collectors.toSet());
 	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public Set<Category> findAllForLevel(String locale, String currency, Long level) {
     	return categoryService.findAllForLevel(locale, currency, level)
     						  .stream().map(c -> dtoToDO(c))
     						  .collect(Collectors.toSet());
  	}	
    

    @Override
	public Set<Category> findAll(String locale, String currency, List<String> codes) {
		// TODO Auto-generated method stub
		return categoryService.findAll(locale, currency, codes)
							  .stream().map(c -> dtoToDO(c))
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
		return "primaryCategory.categoryToken";
	}

	@Override
	public String getFacetCategory() {
		// TODO Auto-generated method stub
		return "CategoryFR";
	}

	@Override
	public Category dtoToDO(io.nzbee.dto.category.Category category) {
		// TODO Auto-generated method stub
		Category categoryDO = category.getType().equals(io.nzbee.dto.category.product.ProductCategory.class.toString()) 
		? new ProductCategory()
		: new BrandCategory();

		categoryDO.setCategoryCode(category.getCategoryCode());
		categoryDO.setCategoryDesc(category.getCategoryDesc());
		categoryDO.setCategoryLevel(category.getCategoryLevel());
		categoryDO.setCount(category.getObjectCount());
		if(category.getType().equals(io.nzbee.dto.category.product.ProductCategory.class.toString())) {
			((ProductCategory) categoryDO).setParentCode(((io.nzbee.dto.category.product.ProductCategory) category).getParentCode());
		}
		categoryDO.setChildCount(category.getChildCategoryCount());
		categoryDO.setLayoutCodes(category.getLayoutCodes());
		
		return categoryDO;
	}


	
}