package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.javabrains.springbootstarter.domain.ProductCategory;
import io.javabrains.springbootstarter.domain.ProductCategoryAttribute;
import io.javabrains.springbootstarter.domain.ProductCategoryAttributeRepository;

@Service
@Transactional
public class ProductCategoryDTOService implements IProductCategoryDTOService {

    @Autowired
    private ProductCategoryAttributeRepository productCategoryAttributeRepository;
    
    //API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
    @Override
	@Transactional
	public List<ProductCategoryDTO> getProductCategories(String lcl) {
		List<ProductCategoryAttribute> lpca = productCategoryAttributeRepository.findByLclCd(lcl);
		final List<ProductCategoryDTO> lpcDTO = new ArrayList<ProductCategoryDTO>();
		for(ProductCategoryAttribute pca : lpca) {
        	ProductCategoryDTO pcDTO = new ProductCategoryDTO();
        	ProductCategory pc = pca.getProductCategory();
        	pcDTO.setCategoryId(pc.getCategoryId());
        	pcDTO.setCategoryCode(pc.getCategoryCode());
        	pcDTO.setCategoryDesc(pca.getCategoryDesc());
        	pcDTO.setChildren(pc.getChildren());
        	pcDTO.setLclCd(pca.getLclCd());
        	lpcDTO.add(pcDTO);
        }
		return lpcDTO;
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
        	pcDTO.setChildren(pc.getChildren());
        	pcDTO.setLclCd(pca.getLclCd());
        	lpcDTO.add(pcDTO);
        }
        return lpcDTO;
 	}	
}