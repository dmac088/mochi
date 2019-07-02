package io.nzbee.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.nzbee.dao.CategoryDAO;
import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.dto.SidebarFacetDTO;
import io.nzbee.entity.ProductRepository;
import io.nzbee.variables.CategoryVars;
import io.nzbee.variables.ProductVars;

@Service
@Transactional
@CacheConfig(cacheNames="categories")
public class CategoryService implements ICategoryService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryDAO categoryDAO;
    
    @Override
	@Transactional
	//@Cacheable
	public List<Category> getCategories(final String locale, String currency) {
    	List<io.nzbee.entity.Category> lpc = categoryDAO.getAll();
    	return lpc.stream()
    			.map(pc -> createCategory(pc, locale, currency))
    			.filter(pc -> pc.getProductCount() > 0)
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
    			
	}	
    
    @Override
 	@Transactional
 	//@Cacheable
 	public List<Category> getCategoryParent(final String locale, String currency, final Long parentCategoryId) {
    	List<io.nzbee.entity.Category> lpc = categoryDAO.getByParent(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, parentCategoryId, locale);
    	return lpc.stream()
    			.map(pc -> createCategory(pc, locale, currency))
    			.filter(pc -> pc.getProductCount() > 0)
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
 	}
    
    @Override
  	@Transactional
  	//@Cacheable
  	public List<Category> getCategoriesForLevel(final String locale, String currency, final Long level) {
     	List<io.nzbee.entity.Category> lpc = categoryDAO.getByLevel(CategoryVars.PRIMARY_HIERARCHY_CODE, CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, level, locale);
     	return lpc.stream()
     			.map(pc -> createCategory(pc, locale, currency))
     			.filter(pc -> pc.getProductCount() > 0)
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
  	}	
    
  
    @Override
  	@Transactional
  	//@Cacheable
  	public Category getCategory(final String locale, String currency, final Long categoryId) {
    	io.nzbee.entity.Category pc = categoryDAO.get(categoryId).get();
     	return	createCategory(pc, locale, currency);
  	}
    
    @Override
	@Transactional
	//@Cacheable
	public Category getCategory(String locale, String currency, String categoryDesc) {
    	io.nzbee.entity.Category pc = categoryDAO.getByCategoryDesc(
						    										CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
						    										categoryDesc, 
						    										locale
						    									   );
     	return	createCategory( 
     							pc, 
     							locale, 
     							currency
     						  );
	}
    
    @Override
	@Transactional
	//@Cacheable
	public List<SidebarFacetDTO> getCategories(String hierarchyCode, String locale, String currency, String categoryDesc, List<SidebarFacetDTO> facets) {
    	
    	List<Long> brandIds = facets.stream().filter(f -> f.getFacetingName().equals(CategoryVars.BRAND_FACET_NAME)).collect(Collectors.toList()) 
    			.stream().map(b -> { return b.getId(); }).collect(Collectors.toList());
    	
    	List<Long> tagIds = facets.stream().filter(f -> f.getFacetingName().equals(CategoryVars.TAG_FACET_NAME)).collect(Collectors.toList()) 
    			.stream().map(b -> { return b.getId(); }).collect(Collectors.toList());
    	
		List<io.nzbee.entity.Category> lc = categoryDAO.get(
				 CategoryVars.PRIMARY_HIERARCHY_CODE, 
				 CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
				 categoryDesc, 
				 brandIds,  
				 tagIds,
				 locale);
		
		List<Category> lcDO = lc.stream().map(c -> createCategory(c, locale, currency)).collect(Collectors.toList());
		
		lcDO.stream().forEach(cDO -> {
			cDO.setProductCount(
								(tagIds.isEmpty()) 
										? 	productRepository.count(
											CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
											cDO.getCategoryDesc(), 
											locale,
											currency,
											ProductVars.MARKDOWN_SKU_DESCRIPTION,
											ProductVars.ACTIVE_SKU_CODE,
											brandIds.size() == 0 ? Arrays.asList(new Long(-1)) : brandIds ,
											(brandIds.size() == 0 ? 0 : 1),
											Arrays.asList(new Long(-1)),
											0)
										: 	productRepository.countForTags(
											CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
											cDO.getCategoryDesc(), 
											locale,
											currency,
											ProductVars.MARKDOWN_SKU_DESCRIPTION,
											ProductVars.ACTIVE_SKU_CODE,
											brandIds.size() == 0 ? Arrays.asList(new Long(-1)) : brandIds ,
											(brandIds.size() == 0 ? 0 : 1),
											Arrays.asList(new Long(-1)),
											0,
											tagIds,
											(tagIds.size() == 0 ? 0 : 1)
											)
								);
			System.out.println(cDO.getProductCount());
		});	
		
		
		List<SidebarFacetDTO> lsfdto = 
			lcDO.stream()
			.filter(c -> c.getProductCount() > 0)
			.map(c -> createCategoryDTO(c)).collect(Collectors.toList()).stream()
			.sorted((o1, o2) -> o1.getDesc().compareTo(o2.getDesc()))
			.collect(Collectors.toList());
		
     	return lsfdto;
	}
    

	@Transactional
	@Cacheable
    private Brand createBrand(final io.nzbee.entity.Brand b, String categoryCode, final String lcl) {
    	final Brand bDto = new Brand();
    	bDto.setBrandId(b.getBrandId());
    	bDto.setBrandCode(b.getBrandCode());
    	bDto.setBrandDesc(
	    	b.getAttributes().stream()
			.filter(ba -> ba.getLclCd().equals(lcl)
			).collect(Collectors.toList()).get(0).getBrandDesc());
    	return bDto;
    }
    
 	@Cacheable
    public Category createCategory(final io.nzbee.entity.Category pc, final String locale, final String currency) {
    	
 		//create a new product DTO
        final Category cDO = new Category();
        cDO.setCategoryId(pc.getCategoryId());
        cDO.setCategoryCode(pc.getCategoryCode());
        cDO.setCategoryLevel(pc.getCategoryLevel());
        
        //get product count and set it
        cDO.setProductCount(	productRepository.count(
								CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
								pc.getAttributes().stream().filter(ca -> ca.getLclCd().equals(locale)).findFirst().get().getCategoryDesc(), 
								locale,
								currency,
								ProductVars.MARKDOWN_SKU_DESCRIPTION,
								ProductVars.ACTIVE_SKU_CODE,
								Arrays.asList(new Long(-1)),
								0,
								Arrays.asList(new Long(-1)),
								0));
        
        
        cDO.setMaxMarkDownPrice(
        		productRepository.maxMarkDownPrice(
								CategoryVars.CATEGORY_TYPE_CODE_PRODUCT, 
								pc.getAttributes().stream().filter(ca -> ca.getLclCd().equals(locale)).findFirst().get().getCategoryDesc(), 
								locale,
								currency,
								ProductVars.MARKDOWN_SKU_DESCRIPTION,
								ProductVars.ACTIVE_SKU_CODE,
								Arrays.asList(new Long(-1)),
								0,
								Arrays.asList(new Long(-1)),
								0));
        		
        //create the child objects and add to children collection
        List<Category> cDOl =
        pc.getChildren().stream().map(pc1 -> {
        	Category pcchild = createCategory(pc1, locale, currency);
        	return pcchild;
        }).filter(c -> c.getProductCount() > 0).collect(Collectors.toList());
        cDO.setChildren(cDOl);

        //set the parentId
        Optional<io.nzbee.entity.Category> parent = Optional.ofNullable(pc.getParent());
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
 	
 	 //Create a data transfer object
    private SidebarFacetDTO createCategoryDTO(final Category c) {
    	final SidebarFacetDTO cDto = new SidebarFacetDTO();
    	cDto.setFacetingName(CategoryVars.PRIMARY_CATEGORY_FACET_NAME);
    	cDto.setFieldName("categoryDesc");
    	cDto.setToken(c.getCategoryCode());
    	cDto.setLevel(new Long(0));
    	cDto.setDesc(c.getCategoryDesc());
    	cDto.setId(c.getCategoryId());
    	cDto.setProductCount(c.getProductCount());
    	cDto.setParent(c.getChildCategoryCount() > 0);
		return cDto;
    }
}