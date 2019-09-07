package io.nzbee.domain.services.category;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.domain.Brand;
import io.nzbee.domain.BrandCategory;
import io.nzbee.domain.Category;
import io.nzbee.domain.ProductCategory;
import io.nzbee.domain.Tag;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.IProductService;
import io.nzbee.variables.CategoryVars;

@Service
@Transactional
@CacheConfig(cacheNames="categories")
public class CategoryServiceImpl implements ICategoryService {
    
    @Autowired
    @Qualifier("productEntityService")
    private IProductService productService;
    
    @Autowired
    @Qualifier("categoryEntityService")
    private io.nzbee.entity.category.ICategoryService categoryService;
    
    @Override
	@Transactional
	//@Cacheable
	public List<Category> findAll(String locale) {
    	List<io.nzbee.entity.category.Category> lpc = categoryService.findAll();
    	return lpc.stream()
    			.map(pc -> createCategory(pc, locale))
    			.collect(Collectors.toList());
	}	


	@Override
	public Category findOneByCode(String locale, String categoryType, String categoryCode) {
		// TODO Auto-generated method stub
		return createCategory(categoryService.findByCategoryCode(
												 categoryType, 
												 categoryCode, 
												 locale).get(), locale);
	}
	

	@Override
	public Category findParent(String locale, Long categoryId) {
		// TODO Auto-generated method stub
		return createCategory(categoryService.findById(categoryId).get().getParent(), locale);
	}
    
    @Override
 	@Transactional
 	//@Cacheable
 	public List<Category> findByParent(final String locale, Long parentCategoryId) {
    	List<io.nzbee.entity.category.Category> lpc = categoryService.findByParent(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, parentCategoryId, locale);
    	return lpc.stream()
    			.map(pc -> createCategory(pc, locale))
    			.collect(Collectors.toList());
 	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public List<Category> findAllForLevel(final String locale, final Long level) {
     	List<io.nzbee.entity.category.Category> lpc = categoryService.findByLevel(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, level, locale);
     	return lpc.stream()
     			.map(pc -> createCategory(pc, locale))
    			.collect(Collectors.toList());
  	}	
    
  
    @Override
  	@Transactional
  	//@Cacheable
  	public Category findOne(final String locale, final Long categoryId) {
    	io.nzbee.entity.category.Category pc = categoryService.findById(categoryId).get();
     	return	createCategory(pc, locale);
  	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public Category findOne(final String locale, final String categoryCode) {
    	io.nzbee.entity.category.Category pc = categoryService.findByCategoryCode(
    			CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
    			categoryCode, 
    			locale).get();
     	return	createCategory(pc, locale);
  	}
    
    @Override
	@Transactional
	//@Cacheable
	public Category findOneByDesc(String locale,  String categoryType, String categoryDesc) {
    	Optional<io.nzbee.entity.category.Category> pc = categoryService.findByCategoryDesc(
    																categoryType, 
						    										categoryDesc, 
						    										locale
						    									   );
     	return	createCategory( 
     							pc.get(), 
     							locale
     						  );
	}
    
    @Override
	@Transactional
	//@Cacheable
	public List<Category> findAll(String locale, String categoryDesc, List<Brand> brands, List<Tag> tags) {
    	
		List<io.nzbee.entity.category.Category> lc = categoryService.find(
				 CategoryVars.PRIMARY_HIERARCHY_CODE, 
				 CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
				 categoryDesc, 
				 brands.stream().map(b -> b.getBrandCode()).collect(Collectors.toList()),  
				 tags.stream().map(t -> t.getTagCode()).collect(Collectors.toList()),
				 locale);
		
		List<Category> lcDO = lc.stream().map(c -> createCategory(c, locale)).collect(Collectors.toList());
			
     	return lcDO;
	}
    
	@Override
 	@Cacheable
    public Category createCategory(final io.nzbee.entity.category.Category pc, final String locale) {
	
 		//create a new product DTO
        final Category cDO = (pc.getCategoryType().getCode().equals(CategoryVars.CATEGORY_TYPE_CODE_PRODUCT)) ? new ProductCategory() : new BrandCategory();
        cDO.setCategoryCode(pc.getCategoryCode());
        cDO.setCategoryLevel(pc.getCategoryLevel());
       
        //set the parentId
        Optional<io.nzbee.entity.category.Category> parent = Optional.ofNullable(pc.getParent());
        if(parent.isPresent()) {
        	cDO.setParentCode(parent.get().getCategoryCode());
        }
        
        cDO.setCategoryDesc(pc.getAttributes().stream()
        		.filter( pa -> pa.getLclCd().equals(locale)).collect(Collectors.toList()).get(0).getCategoryDesc());
        cDO.setLclCd(locale);
        cDO.setChildCategoryCount(pc.getChildCategoryCount());
        
        cDO.setCategoryType(pc.getCategoryType().getCode());
        cDO.setLayouts(pc.getLayouts());
        return cDO;
    }

}