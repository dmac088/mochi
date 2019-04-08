package io.javabrains.springbootstarter.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.domain.Brand;
import io.javabrains.springbootstarter.domain.ProductCategory;
import io.javabrains.springbootstarter.domain.ProductCategoryRepository;
import io.javabrains.springbootstarter.domain.ProductRepository;

@Service
@Transactional
public class ProductCategoryDTOService implements IProductCategoryDTOService {
    
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    //API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
    @Override
	@Transactional
	public List<ProductCategoryDTO> getProductCategories(final String lcl, String currency) {
    	List<ProductCategory> lpc = productCategoryRepository.findAll();
    	return lpc.stream().map(pc -> convertToProductCategoryDto(pc, lcl, currency))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
    			
	}	
    
    @Override
 	@Transactional
 	public List<ProductCategoryDTO> getProductCategoryParent(final String lcl, String currency, final Long parentCategoryId) {
    	List<ProductCategory> lpc = productCategoryRepository.findByParentCategoryId(parentCategoryId);
    	return lpc.stream().map(pc -> convertToProductCategoryDto(pc, lcl, currency))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
 	}
    
    @Override
  	@Transactional
  	public List<ProductCategoryDTO> getProductCategoriesForLevel(final String lcl, String currency, final Long level) {
     	List<ProductCategory> lpc = productCategoryRepository.findByCategoryLevel(level);
     	return lpc.stream().map(pc -> convertToProductCategoryDto(pc, lcl, currency))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
  	}	
    
    @Override
  	@Transactional
  	public List<ProductCategoryDTO> getPreviewProductCategories(final String lcl, String currency, final Long previewFlag) {
        List<ProductCategory> lpc = productCategoryRepository.findByPreviewFlag(previewFlag);
        return lpc.stream().map(pc -> convertToProductCategoryDto(pc, lcl, currency))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
  	}
    
    @Override
  	@Transactional
  	public ProductCategoryDTO getProductCategory(final String lcl, String currency, final Long categoryId) {
     	ProductCategory pc = productCategoryRepository.findByCategoryId(categoryId);
     	return	convertToProductCategoryDto(pc, lcl, currency);
  	}
    
    @Override
	public ProductCategoryDTO getProductCategory(String lcl, String currency, String categoryDesc) {
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeCategoryDesc(categoryDesc);
     	return	convertToProductCategoryDto(pc, lcl, currency);
	}
    
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
    
    private ProductCategoryDTO convertToProductCategoryDto(final ProductCategory pc, final String lcl, final String currency) {
    	
        final ProductCategoryDTO pcDto = new ProductCategoryDTO();
        pcDto.setCategoryId(pc.getCategoryId());
        pcDto.setCategoryCode(pc.getCategoryCode());
        pcDto.setCategoryLevel(pc.getCategoryLevel());
        
        //get product count and set it
        pcDto.setProductCount(productRepository.countByCategoriesCategoryCode(pc.getCategoryCode()));
        pcDto.setMaxMarkDownPrice(productRepository.maxMarkDownPriceByCategoriesCategoryCodeAndPriceCurrencyCode(pc.getCategoryCode(), currency));
        
        //set the brand attributes of all products within the category, to the localized version
        Set<BrandDTO> catBrands = pc.getProducts().stream().map(p -> this.convertToBrandDto(p.getBrand(), pc.getCategoryCode(), lcl)).collect(Collectors.toSet());
       		
        
        //create the child objects and add to children collection
        List<ProductCategoryDTO> pcDTOl =
        pc.getChildren().stream().map(pc1 -> {
        	ProductCategoryDTO pcchild = convertToProductCategoryDto(pc1, lcl, currency);
        	catBrands.addAll(pcchild.getCategoryBrands());
        	return pcchild;
        }).collect(Collectors.toList());
        pcDto.setChildren(pcDTOl);
        
        catBrands.forEach(b -> b.setProductCount(productRepository.countByCategoriesCategoryCodeAndBrandBrandCode(pcDto.getCategoryCode(), b.getBrandCode())));
        catBrands.forEach(b -> b.setMaxMarkDownPrice(productRepository.maxMarkDownPriceByCategoriesCategoryCodeAndBrandBrandCodeAndPriceCurrencyCode(pcDto.getCategoryCode(), b.getBrandCode(), currency)+1));
        pcDto.setCategoryBrands(catBrands);
        
       
        //set the parentId
        if(!(pc.getParent() == null)) {
        	pcDto.setParentId(pc.getParent().getCategoryId());
        }
        pcDto.setCategoryPreview(pc.getPreviewFlag());
        pcDto.setCategoryMenu(pc.getMenuDisplayFlag());
        pcDto.setLandingDisplay(pc.getLandingDisplayFlag());
        pcDto.setCategoryDesc(pc.getProductCategoryAttribute().stream()
        		.filter( pa -> pa.getLclCd().equals(lcl)).collect(Collectors.toList()).get(0).getCategoryDesc());
        pcDto.setLclCd(lcl);
        pcDto.setChildCategoryCount(new Long(pc.getChildren().size()));
        
        return pcDto;
    }
	
}