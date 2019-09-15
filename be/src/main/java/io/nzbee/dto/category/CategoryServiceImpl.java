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

@Service
@Transactional
@CacheConfig(cacheNames="categories")
public class CategoryServiceImpl implements ICategoryService {
    
   
    @Autowired
    @Qualifier("categoryDomainDao")
    private io.nzbee.dto.category.ICategoryDao categoryDao;
    
    @Override
	@Transactional
	//@Cacheable
	public List<Category> findAll(String locale) {
    	return  categoryDao.findAll(locale);
	}	


	@Override
	public Optional<Category> findOneByCode(String locale, String categoryCode) {
		// TODO Auto-generated method stub
		return categoryDao.findByCategoryCode(categoryCode, 
											  locale);
	}
	

	@Override
	public Optional<Category> findParent(String locale, Long categoryId) {
		// TODO Auto-generated method stub
		return categoryDao.findByCategoryCode(categoryDao.findById(categoryId).get().getParentCode(), locale);
	}
    
    @Override
 	@Transactional
 	//@Cacheable
 	public List<Category> findByParent(final String locale, Long parentCategoryId) {
    	return categoryDao.findByParent(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, parentCategoryId, locale);
    	
 	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public List<Category> findAllForLevel(final String locale, final Long level) {
     	return categoryDao.findByLevel(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, level, locale);
  	}	
    
  
    @Override
  	@Transactional
  	//@Cacheable
  	public Optional<Category> findOne(final String locale, final Long categoryId) {
    	return categoryDao.findById(categoryId);
  	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public Optional<Category> findOne(final String locale, final String categoryCode) {
    	return categoryDao.findByCategoryCode(
    			categoryCode, 
    			locale);
  	}
    
    @Override
	@Transactional
	//@Cacheable
	public Optional<Category> findOneByDesc(String locale,  String categoryType, String categoryDesc) {
    	return categoryDao.findByCategoryDesc(categoryDesc, 
						    				  locale);
   
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
    
//	@Override
// 	@Cacheable
//    public Category createCategory(final io.nzbee.entity.category.Category pc, final String locale) {
//	
// 		//create a new product DTO
//        final Category cDO = (pc.getCategoryType().getCode().equals(CategoryVars.CATEGORY_TYPE_CODE_PRODUCT)) ? new ProductCategory() : new BrandCategory();
//        cDO.setCategoryCode(pc.getCategoryCode());
//        cDO.setCategoryLevel(pc.getCategoryLevel());
//        cDO.setCount(pc.getCount());
//       
//        //set the parentId
//        Optional<io.nzbee.entity.category.Category> parent = Optional.ofNullable(pc.getParent());
//        if(parent.isPresent()) {
//        	cDO.setParentCode(parent.get().getCategoryCode());
//        }
//        
//        cDO.setCategoryDesc(pc.getAttributes().stream()
//        		.filter( pa -> pa.getLclCd().equals(locale)).collect(Collectors.toList()).get(0).getCategoryDesc());
//        cDO.setLclCd(locale);
//        cDO.setChildCategoryCount(pc.getChildCategoryCount());
//        
//        cDO.setCategoryType(pc.getCategoryType().getCode());
//        return cDO;
//    }

}