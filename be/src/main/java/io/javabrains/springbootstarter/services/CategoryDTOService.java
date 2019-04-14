package io.javabrains.springbootstarter.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.domain.Brand;
import io.javabrains.springbootstarter.domain.Category;
import io.javabrains.springbootstarter.domain.CategoryRepository;
import io.javabrains.springbootstarter.domain.ProductRepository;

@Service
@Transactional
@CacheConfig(cacheNames="categories")
public class CategoryDTOService implements ICategoryDTOService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    //API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
    @Override
	@Transactional
	@Cacheable
	public List<CategoryDTO> getCategories(final String lcl, String currency) {
    	List<Category> lpc = categoryRepository.findAll();
    	return lpc.stream().map(pc -> convertToCategoryDto(pc, lcl, currency))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
    			
	}	
    
    @Override
 	@Transactional
 	@Cacheable
 	public List<CategoryDTO> getCategoryParent(final String lcl, String currency, final Long parentCategoryId) {
    	List<Category> lpc = categoryRepository.findByParentCategoryId(parentCategoryId);
    	return lpc.stream().map(pc -> convertToCategoryDto(pc, lcl, currency))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
 	}
    
    @Override
  	@Transactional
  	@Cacheable
  	public List<CategoryDTO> getCategoriesForLevel(final String lcl, String currency, final Long level) {
     	List<Category> lpc = categoryRepository.findByCategoryLevelAndCategoryTypeCode(level, "PRD01");
     	return lpc.stream().map(pc -> convertToCategoryDto(pc, lcl, currency))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
  	}	
    
  
    @Override
  	@Transactional
  	@Cacheable
  	public CategoryDTO getCategory(final String lcl, String currency, final Long categoryId) {
     	Category pc = categoryRepository.findByCategoryId(categoryId);
     	return	convertToCategoryDto(pc, lcl, currency);
  	}
    
    @Override
    @Cacheable
	public CategoryDTO getCategory(String lcl, String currency, String categoryDesc) {
     	Category pc = categoryRepository.findByAttributesCategoryDesc(categoryDesc);
     	return	convertToCategoryDto(pc, lcl, currency);
	}
    
 	@Cacheable
    private BrandDTO convertToBrandDto(final Brand b, String categoryCode, final String lcl) {
    	final BrandDTO bDto = new BrandDTO();
    	bDto.setBrandId(b.getBrandId());
    	bDto.setBrandCode(b.getBrandCode());
    	bDto.setBrandDesc(
	    	b.getBrandAttributes().stream()
			.filter(ba -> ba.getLclCd().equals(lcl)
			).collect(Collectors.toList()).get(0).getbrandDesc());
    	return bDto;
    }
    
 	@Cacheable
    private CategoryDTO convertToCategoryDto(final Category pc, final String lcl, final String currency) {
    	
        final CategoryDTO pcDto = new CategoryDTO();
        pcDto.setCategoryId(pc.getCategoryId());
        pcDto.setCategoryCode(pc.getCategoryCode());
        pcDto.setCategoryLevel(pc.getCategoryLevel());
        
        //get product count and set it
        pcDto.setProductCount(productRepository.countByCategoriesCategoryCode(pc.getCategoryCode()));
        pcDto.setMaxMarkDownPrice(productRepository.maxMarkDownPriceByCategoriesCategoryCodeAndPriceCurrencyCode(pc.getCategoryCode(), currency));
        
        //set the brand attributes of all products within the category, to the localized version
        Set<BrandDTO> catBrands = pc.getProducts().stream().map(p -> this.convertToBrandDto(p.getBrand(), pc.getCategoryCode(), lcl)).collect(Collectors.toSet());
       		
        
        //create the child objects and add to children collection
        List<CategoryDTO> pcDTOl =
        pc.getChildren().stream().map(pc1 -> {
        	CategoryDTO pcchild = convertToCategoryDto(pc1, lcl, currency);
        	catBrands.addAll(pcchild.getCategoryBrands());
        	return pcchild;
        }).collect(Collectors.toList());
        pcDto.setChildren(pcDTOl);
        
        catBrands.forEach(b -> b.setProductCount(productRepository.countByCategoriesCategoryCodeAndBrandBrandCode(pcDto.getCategoryCode(), b.getBrandCode())));
        catBrands.forEach(b -> b.setMaxMarkDownPrice(productRepository.maxMarkDownPriceByCategoriesCategoryCodeAndBrandBrandCodeAndPriceCurrencyCode(pcDto.getCategoryCode(), b.getBrandCode(), currency)));
        pcDto.setCategoryBrands(catBrands);
        
       
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