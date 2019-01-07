package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.springbootstarter.domain.ProductAttribute;
import io.javabrains.springbootstarter.domain.ProductCategory;
import io.javabrains.springbootstarter.domain.ProductCategoryAttribute;
import io.javabrains.springbootstarter.domain.ProductCategoryAttributeRepository;
import io.javabrains.springbootstarter.domain.ProductCategoryRepository;

@Service
@Transactional
public class ProductCategoryDTOService implements IProductCategoryDTOService {

    @Autowired
    private ProductCategoryAttributeRepository productCategoryAttributeRepository;
    
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    
    //API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
    @Override
	@Transactional
	public List<ProductCategoryDTO> getProductCategories(String lcl) {
    	
    	List<ProductCategory> lpc = productCategoryRepository.findAll();
    	
		final List<ProductCategoryDTO> lpcDTO = new ArrayList<ProductCategoryDTO>();
		
		for(ProductCategory pc : lpc) {
        	lpcDTO.add(convertToProductCategoryDto(pc, lcl));
        }
		return lpcDTO;
	}	
    
    private ProductCategoryDTO convertToProductCategoryDto(final ProductCategory pc,final String lcl) {
    	ProductCategoryAttribute pca = productCategoryAttributeRepository.findByLclCdAndCategoryId(lcl, pc.getCategoryId()).get();	
        final ProductCategoryDTO pcDto = new ProductCategoryDTO();
        pcDto.setCategoryId(pc.getCategoryId());
        pcDto.setCategoryCode(pc.getCategoryCode());
        List<ProductCategoryDTO> pcDTOl = new ArrayList<ProductCategoryDTO>();
        for(ProductCategory pc1 : pc.getChildren()) {
        	pcDTOl.add(convertToProductCategoryDto(pc1, lcl));
        }
        pcDto.setChildren(pcDTOl);
        pcDto.setCategoryDesc(pca.getCategoryDesc());
        pcDto.setLclCd(pca.getLclCd());
        return pcDto;
    }
    
    @Override
 	@Transactional
 	public List<ProductCategoryDTO> getProductCategories(String lcl, Long parentCategoryId) {
 		List<ProductCategoryAttribute> lpca = productCategoryAttributeRepository.findByLclCdAndProductCategoryParentCategoryId(lcl, parentCategoryId);
        final List<ProductCategoryDTO> lpcDTO = new ArrayList<ProductCategoryDTO>();
        for(ProductCategoryAttribute pca : lpca) {
        	ProductCategoryDTO pcDTO = new ProductCategoryDTO();
        	ProductCategory pc = pca.getProductCategory();
        	pcDTO.setCategoryId(pc.getCategoryId());
        	pcDTO.setCategoryCode(pc.getCategoryCode());
        	pcDTO.setCategoryDesc(pca.getCategoryDesc());
        	//pcDTO.setParent(pc.getParent());
        	pcDTO.setChildren(pc.getChildren());
        	pcDTO.setLclCd(pca.getLclCd());
        	lpcDTO.add(pcDTO);
        }
        return lpcDTO;
 	}	
}