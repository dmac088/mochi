package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
	public List<ProductCategoryDTO> getProductCategories(final String lcl) {
    	
    	List<ProductCategory> lpc = productCategoryRepository.findAll();
    	
		final List<ProductCategoryDTO> lpcDTO = new ArrayList<ProductCategoryDTO>();
		
		for(ProductCategory pc : lpc) {
        	lpcDTO.add(convertToProductCategoryDto(pc, lcl));
        }
		
		return lpcDTO;
	}	
    
    @Override
 	@Transactional
 	public List<ProductCategoryDTO> getProductCategoryParent(final String lcl, final Long parentCategoryId) {
    	
    	List<ProductCategory> lpc = productCategoryRepository.findByParentCategoryId(parentCategoryId);
    	
		final List<ProductCategoryDTO> lpcDTO = new ArrayList<ProductCategoryDTO>();
		
		for(ProductCategory pc : lpc) {
        	lpcDTO.add(convertToProductCategoryDto(pc, lcl));
        }
		return lpcDTO;
 	}
    
    @Override
  	@Transactional
  	public List<ProductCategoryDTO> getProductCategoriesForLevel(final String lcl, final Long level) {
     	
     	List<ProductCategory> lpc = productCategoryRepository.findByCategoryLevel(level);
     	
 		final List<ProductCategoryDTO> lpcDTO = new ArrayList<ProductCategoryDTO>();
 		
 		for(ProductCategory pc : lpc) {
         	lpcDTO.add(convertToProductCategoryDto(pc, lcl));
         }
 		return lpcDTO;
  	}	
    
    @Override
  	@Transactional
  	public ProductCategoryDTO getProductCategory(final String lcl, final Long categoryId) {
     	ProductCategory pc = productCategoryRepository.findByCategoryId(categoryId).get();
     	return	convertToProductCategoryDto(pc, lcl);
  	}
    
    private ProductCategoryDTO convertToProductCategoryDto(final ProductCategory pc, final String lcl) {
    	ProductCategoryAttribute pca = productCategoryAttributeRepository.findByLclCdAndCategoryId(lcl, pc.getCategoryId()).get();	
        final ProductCategoryDTO pcDto = new ProductCategoryDTO();
        pcDto.setProductCount(new Long(pc.getProducts().size()));
        pcDto.setCategoryId(pc.getCategoryId());
        pcDto.setCategoryCode(pc.getCategoryCode());
        pcDto.setCategoryLevel(pc.getCategoryLevel());
        List<ProductCategoryDTO> pcDTOl = new ArrayList<ProductCategoryDTO>();
        for(ProductCategory pc1 : pc.getChildren()) {
        	ProductCategoryDTO pcchild = convertToProductCategoryDto(pc1, lcl);
        	pcDto.setProductCount(pcDto.getProductCount() + pc.getProducts().size() + pc1.getProducts().size());
        	pcchild.setProductCount(new Long(pc1.getProducts().size()));
        	pcDTOl.add(pcchild);
        }
        pcDto.setChildren(pcDTOl);
        pcDto.setCategoryDesc(pca.getCategoryDesc());
        pcDto.setLclCd(pca.getLclCd());
        pcDto.setChildCategoryCount(new Long(pc.getChildren().size()));
        return pcDto;
    }
}