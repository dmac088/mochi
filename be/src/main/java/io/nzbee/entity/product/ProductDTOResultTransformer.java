package io.nzbee.entity.product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;

import io.nzbee.Constants;
import io.nzbee.entity.brand.BrandDTO;
import io.nzbee.entity.category.product.CategoryProductDTO;
import io.nzbee.entity.product.department.DepartmentDTO;
import io.nzbee.entity.product.physical.PhysicalProductDTO;
import io.nzbee.entity.product.shipping.ShippingProductDTO;
import io.nzbee.entity.promotion.PromotionDTO;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicDTO;

public class ProductDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;
	
	private Map<Long, ProductDTO> productDTOMap = new LinkedHashMap<>();
	
	private Map<Long, CategoryProductDTO> categoryProductDTOMap = new LinkedHashMap<>();
	
	private Map<Long, PromotionDTO> promotionDTOMap = new LinkedHashMap<>();
	
	private Map<Long, PromotionMechanicDTO> promotionMechanicDTOMap = new LinkedHashMap<>();
	
	private Map<String, Integer> aliasToIndexMap;
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		
		if (aliasToIndexMap == null) {
            aliasToIndexMap = aliasToIndexMap(aliases);
        }
		
        Long productId = ((Number) tuple[aliasToIndexMap.get(ProductDTO.ID_ALIAS)]).longValue();
        
        DepartmentDTO pd = new DepartmentDTO(tuple, aliasToIndexMap);
        
        BrandDTO pb = new BrandDTO(tuple, aliasToIndexMap);
 
        ProductDTO productDTO = productDTOMap.computeIfAbsent(
            productId,
            id -> {
            	ProductDTO pDto = null;  
            	switch(pd.getDepartmentCode()) {
            		case Constants.physicalProductDepartmentCode:
            			pDto = new PhysicalProductDTO(tuple, aliasToIndexMap);
            		case Constants.shippingProductDepartmentCode:
            			pDto = new ShippingProductDTO(tuple, aliasToIndexMap);
            		default:
            			pDto = new PhysicalProductDTO(tuple, aliasToIndexMap);
            	}
            			
            	if(!( tuple[aliasToIndexMap.get(PromotionDTO.ID_ALIAS)] == null)) {
                	
                	Long promotionId = ((Number) tuple[aliasToIndexMap.get(PromotionDTO.ID_ALIAS)]).longValue();
                	
                	PromotionDTO promotionDTO = promotionDTOMap.computeIfAbsent(
                		promotionId,
            	        pId -> {
            	            PromotionDTO promoDto = new PromotionDTO(tuple, aliasToIndexMap);
            	            
            	            Long promotionMechanicId = ((Number) tuple[aliasToIndexMap.get(PromotionMechanicDTO.ID_ALIAS)]).longValue();
                        	
                        	PromotionMechanicDTO promotionMechanic = promotionMechanicDTOMap.computeIfAbsent(
                        		promotionMechanicId,
                        		pMechanicId -> {
                        			PromotionMechanicDTO promoMechDto = new PromotionMechanicDTO(tuple, aliasToIndexMap);
                        			return promoMechDto;
                        		}		
                        	);
                        	
                        	promoDto.setMechanicDTO(promotionMechanic);
            	            
            	            return promoDto;
            	        }
                	);
                	
                	pDto.getPromotions().add(promotionDTO);
                	

                }
            	
            	pDto.setBrand(pb);
                
            	pDto.setDepartment(pd);
                 
            	return pDto;
            }
        );
        
        Long categoryId = ((Number) tuple[aliasToIndexMap.get(CategoryProductDTO.ID_ALIAS)]).longValue();
        
        categoryProductDTOMap.computeIfAbsent(
                categoryId,
                id -> {
                	CategoryProductDTO cDto = new CategoryProductDTO(tuple, aliasToIndexMap);
                	productDTO.getCategories().add(
                            new CategoryProductDTO(tuple, aliasToIndexMap)
                    );
                	return cDto;
                }
        );
 
        return productDTO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return new ArrayList<>(productDTOMap.values());
	}
	
	
	public Map<String, Integer> aliasToIndexMap(
	        String[] aliases) {
	     
	    Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();
	     
	    for (int i = 0; i < aliases.length; i++) {
	        aliasToIndexMap.put(aliases[i], i);
	    }
	     
	    return aliasToIndexMap;
	}

}
