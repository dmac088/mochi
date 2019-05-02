package io.javabrains.springbootstarter.services;

import java.util.List;
//import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import io.javabrains.springbootstarter.domain.Brand;
import io.javabrains.springbootstarter.domain.Category;
import io.javabrains.springbootstarter.entity.CategoryRepository;
import io.javabrains.springbootstarter.entity.ProductRepository;

@Service
@Transactional
@CacheConfig(cacheNames="categories")
public class CategoryService implements ICategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
	@Transactional
	@Cacheable
	public List<Category> getCategories(final String lcl, String currency) {
    	List<io.javabrains.springbootstarter.entity.Category> lpc = categoryRepository.findAll();
    	return lpc.stream().map(pc -> createCategory(pc, lcl, currency))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
    			
	}	
    
    @Override
 	@Transactional
 	@Cacheable
 	public List<Category> getCategoryParent(final String lcl, String currency, final Long parentCategoryId) {
    	List<io.javabrains.springbootstarter.entity.Category> lpc = categoryRepository.findByParentCategoryId(parentCategoryId);
    	return lpc.stream().map(pc -> createCategory(pc, lcl, currency))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
 	}
    
    @Override
  	@Transactional
  	@Cacheable
  	public List<Category> getCategoriesForLevel(final String lcl, String currency, final Long level) {
     	List<io.javabrains.springbootstarter.entity.Category> lpc = categoryRepository.findByCategoryLevelAndCategoryTypeCode(level, "PRD01");
     	return lpc.stream().map(pc -> createCategory(pc, lcl, currency))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
  	}	
    
  
    @Override
  	@Transactional
  	@Cacheable
  	public Category getCategory(final String lcl, String currency, final Long categoryId) {
    	io.javabrains.springbootstarter.entity.Category pc = categoryRepository.findByCategoryId(categoryId);
     	return	createCategory(pc, lcl, currency);
  	}
    
    @Override
    @Cacheable
	public Category getCategory(String lcl, String currency, String categoryDesc) {
    	io.javabrains.springbootstarter.entity.Category pc = categoryRepository.findByAttributesCategoryDesc(categoryDesc);
     	return	createCategory(pc, lcl, currency);
	}
    
 	@Cacheable
    private Brand createBrand(final io.javabrains.springbootstarter.entity.Brand b, String categoryCode, final String lcl) {
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
    public Category createCategory(final io.javabrains.springbootstarter.entity.Category pc, final String lcl, final String currency) {
    	
 		//create a new product DTO
        final Category pcDto = new Category();
        pcDto.setCategoryId(pc.getCategoryId());
        pcDto.setCategoryCode(pc.getCategoryCode());
        pcDto.setCategoryLevel(pc.getCategoryLevel());
        
        //get product count and set it
        pcDto.setProductCount(productRepository.countByCategoriesCategoryCode(pc.getCategoryCode()));
        pcDto.setMaxMarkDownPrice(productRepository.maxMarkDownPriceByCategoriesCategoryCodeAndPriceCurrencyCode(pc.getCategoryCode(), currency));
        
        //set the brand attributes of all products within the category, to the localized version
        //Set<Brand> catBrands = pc.getProducts().stream().map(p -> this.createBrand(p.getBrand(), pc.getCategoryCode(), lcl)).collect(Collectors.toSet());
       		
        //create the child objects and add to children collection
        List<Category> pcDTOl =
        pc.getChildren().stream().map(pc1 -> {
        	Category pcchild = createCategory(pc1, lcl, currency);
        	return pcchild;
        }).collect(Collectors.toList());
        pcDto.setChildren(pcDTOl);
        
        //get the counts for the brands within the category
        //catBrands.forEach(b -> b.setProductCount(productRepository.countByCategoriesCategoryCodeAndBrandBrandCode(pcDto.getCategoryCode(), b.getBrandCode())));
        //catBrands.forEach(b -> b.setMaxMarkDownPrice(productRepository.maxMarkDownPriceByCategoriesCategoryCodeAndBrandBrandCodeAndPriceCurrencyCode(pcDto.getCategoryCode(), b.getBrandCode(), currency)));
       
        //set the parentId
        if(!(pc.getParent() == null)) {
        	pcDto.setParentId(pc.getParent().getCategoryId());
        }
        
        pcDto.setCategoryDesc(pc.getAttributes().stream()
        		.filter( pa -> pa.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getCategoryDesc());
        pcDto.setLclCd(lcl);
        pcDto.setChildCategoryCount(new Long(pc.getChildren().size()));
        pcDto.setCategoryType(pc.getCategoryType().getCode());
        pcDto.setLayouts(pc.getLayouts());
        return pcDto;
    }
	
}