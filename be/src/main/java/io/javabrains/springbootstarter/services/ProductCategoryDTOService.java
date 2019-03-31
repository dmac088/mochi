package io.javabrains.springbootstarter.services;

import java.util.Set;
import java.util.List;
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
	public List<ProductCategoryDTO> getProductCategories(final String lcl) {
    	List<ProductCategory> lpc = productCategoryRepository.findAll();
    	return lpc.stream().map(pc -> convertToProductCategoryDto(pc, lcl))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
    			
	}	
    
    @Override
 	@Transactional
 	public List<ProductCategoryDTO> getProductCategoryParent(final String lcl, final Long parentCategoryId) {
    	List<ProductCategory> lpc = productCategoryRepository.findByParentCategoryId(parentCategoryId);
    	return lpc.stream().map(pc -> convertToProductCategoryDto(pc, lcl))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
 	}
    
    @Override
  	@Transactional
  	public List<ProductCategoryDTO> getProductCategoriesForLevel(final String lcl, final Long level) {
     	List<ProductCategory> lpc = productCategoryRepository.findByCategoryLevel(level);
     	return lpc.stream().map(pc -> convertToProductCategoryDto(pc, lcl))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
  	}	
    
    @Override
  	@Transactional
  	public List<ProductCategoryDTO> getPreviewProductCategories(final String lcl, final Long previewFlag) {
        List<ProductCategory> lpc = productCategoryRepository.findByPreviewFlag(previewFlag);
        return lpc.stream().map(pc -> convertToProductCategoryDto(pc, lcl))
    			.sorted((pc1, pc2) -> pc2.getProductCount().compareTo(pc1.getProductCount()))
    			.collect(Collectors.toList());
  	}
    
    @Override
  	@Transactional
  	public ProductCategoryDTO getProductCategory(final String lcl, final Long categoryId) {
     	ProductCategory pc = productCategoryRepository.findByCategoryId(categoryId);
     	return	convertToProductCategoryDto(pc, lcl);
  	}
    
    @Override
	public ProductCategoryDTO getProductCategory(String lcl, String categoryDesc) {
     	ProductCategory pc = productCategoryRepository.findByProductCategoryAttributeLclCdAndProductCategoryAttributeCategoryDesc(lcl, categoryDesc);
     	return	convertToProductCategoryDto(pc, lcl);
	}
    
    private BrandDTO convertToBrandDto(final Brand b, final String lcl) {
    	final BrandDTO bDto = new BrandDTO();
    	bDto.setBrandId(b.getBrandId());
    	bDto.setBrandDesc(
	    	b.getBrandAttributes().stream()
			.filter(ba -> ba.getLclCd().equals(lcl)
			).collect(Collectors.toList()).get(0).getbrandDesc());
    	
    	return bDto;
    }
    
    private ProductCategoryDTO convertToProductCategoryDto(final ProductCategory pc, final String lcl) {
    	
        final ProductCategoryDTO pcDto = new ProductCategoryDTO();
        pcDto.setCategoryId(pc.getCategoryId());
        pcDto.setCategoryCode(pc.getCategoryCode());
        pcDto.setCategoryLevel(pc.getCategoryLevel());
        
        //get product count and set it
        pcDto.setProductCount(productRepository.countByCategoriesCategoryCode(pc.getCategoryCode()));
        
        //set the brand attributes of all products within the category, to the localized version
        Set<BrandDTO> hba = pc.getProducts().stream().map(p -> this.convertToBrandDto(p.getBrand(), lcl)).collect(Collectors.toSet());
        					
        
        //create the child objects and add to children collection
        List<ProductCategoryDTO> pcDTOl =
        pc.getChildren().stream().map(pc1 -> {
        	ProductCategoryDTO pcchild = convertToProductCategoryDto(pc1, lcl);
        	pcDto.setProductCount(pcDto.getProductCount() + pcchild.getProductCount());
        	hba.addAll(pcchild.getCategoryBrands());
        	return pcchild;
        }).collect(Collectors.toList());
        pcDto.setChildren(pcDTOl);
        pcDto.setCategoryBrands(hba);
        //all the children now have their product count set
     			 
        		
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