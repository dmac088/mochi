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
import io.nzbee.domain.Category;
import io.nzbee.entity.product.IProductService;
import io.nzbee.variables.CategoryVars;

@Service
@Transactional
@CacheConfig(cacheNames="categories")
public class CategoryService implements ICategoryService {
    
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
    	List<io.nzbee.entity.category.Category> lpc = categoryService.getAll();
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
	public List<Category> findAll(String locale, String categoryDesc, List<Long> brandIds, List<Long> tagIds) {
    	
		List<io.nzbee.entity.category.Category> lc = categoryService.find(
				 CategoryVars.PRIMARY_HIERARCHY_CODE, 
				 CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
				 categoryDesc, 
				 brandIds,  
				 tagIds,
				 locale);
		
		List<Category> lcDO = lc.stream().map(c -> createCategory(c, locale)).collect(Collectors.toList());
		
//		lcDO.stream().forEach(cDO -> {
//			cDO.setProductCount(
//								(tagIds.isEmpty()) 
//										? 	productService.getCount(
//											cDO.getCategoryDesc(), 
//											locale,
//											ProductVars.ACTIVE_SKU_CODE,
//											brandIds.size() == 0 ? Arrays.asList(new Long(-1)) : brandIds ,
//											(brandIds.size() == 0 ? 0 : 1),
//											Arrays.asList(new Long(-1)),
//											0)
//										: 	productService.getCountForTags(
//											cDO.getCategoryDesc(), 
//											locale,
//											ProductVars.ACTIVE_SKU_CODE,
//											brandIds.size() == 0 ? Arrays.asList(new Long(-1)) : brandIds ,
//											(brandIds.size() == 0 ? 0 : 1),
//											Arrays.asList(new Long(-1)),
//											0,
//											tagIds,
//											(tagIds.size() == 0 ? 0 : 1)
//											)
//								);
//		});		
     	return lcDO;
	}
    

	@Transactional
	@Cacheable
    private Brand createBrand(final io.nzbee.entity.brand.Brand b, String categoryCode, final String lcl) {
    	final Brand bDto = new Brand();
    	bDto.setBrandId(b.getId());
    	bDto.setBrandCode(b.getCode());
    	bDto.setBrandDesc(
	    	b.getAttributes().stream()
			.filter(ba -> ba.getLclCd().equals(lcl)
			).collect(Collectors.toList()).get(0).getBrandDesc());
    	return bDto;
    }
    
 	@Cacheable
    public Category createCategory(final io.nzbee.entity.category.Category pc, final String locale) {
    	
 		//create a new product DTO
        final Category cDO = new Category();
        cDO.setCategoryId(pc.getCategoryId());
        cDO.setCategoryCode(pc.getCategoryCode());
        cDO.setCategoryLevel(pc.getCategoryLevel());
        
        //get product count and set it
//        cDO.setProductCount(	productService.getCount(
//								pc.getAttributes().stream().filter(ca -> ca.getLclCd().equals(locale)).findFirst().get().getCategoryDesc(), 
//								locale,
//								ProductVars.ACTIVE_SKU_CODE,
//								Arrays.asList(new Long(-1)),
//								0,
//								Arrays.asList(new Long(-1)),
//								0));
        
        
//        cDO.setMaxMarkDownPrice(
//        						productService.getMaxMarkDownPrice(
//								CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
//								pc.getAttributes().stream().filter(ca -> ca.getLclCd().equals(locale)).findFirst().get().getCategoryDesc(), 
//								locale,
//								currency,
//								ProductVars.ACTIVE_SKU_CODE,
//								Arrays.asList(new Long(-1)),
//								0,
//								Arrays.asList(new Long(-1)),
//								0));
        		
        //create the child objects and add to children collection
        List<Category> cDOl =
        pc.getChildren().stream().map(pc1 -> {
        	Category pcchild = createCategory(pc1, locale);
        	return pcchild;
        }).collect(Collectors.toList());//.filter(c -> c.getProductCount() > 0).collect(Collectors.toList());
        //cDO.setChildren(cDOl);

        //set the parentId
        Optional<io.nzbee.entity.category.Category> parent = Optional.ofNullable(pc.getParent());
        if(parent.isPresent()) {
        	cDO.setParentId(parent.get().getCategoryId());
        }
        
        cDO.setCategoryDesc(pc.getAttributes().stream()
        		.filter( pa -> pa.getLclCd().equals(locale)).collect(Collectors.toList()).get(0).getCategoryDesc());
        cDO.setLclCd(locale);
        cDO.setChildCategoryCount(new Long(pc.getChildren().size()));
        cDO.setCategoryType(pc.getCategoryType().getCode());
        cDO.setLayouts(pc.getLayouts());
        return cDO;
    }

}