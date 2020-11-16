package io.nzbee.entity.bag;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;
import io.nzbee.entity.bag.BagDTO;
import io.nzbee.entity.bag.item.BagItemDTO;
import io.nzbee.entity.brand.BrandDTO;
import io.nzbee.entity.party.person.CustomerDTO;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.entity.product.department.DepartmentDTO;

public class BagDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;
	
	private Map<Long, BagDTO> bagDTOMap 				= new LinkedHashMap<>();
	
	private Map<Long, BagItemDTO> bagItemDTOMap 		= new LinkedHashMap<>();
	
	private Map<Long, ProductDTO> productDTOMap 		= new LinkedHashMap<>();
	
	private Map<Long, BrandDTO> brandDTOMap 			= new LinkedHashMap<>();
	
	private Map<Long, DepartmentDTO> departmentDTOMap 	= new LinkedHashMap<>();
	
	private Map<Long, CustomerDTO> customerDTOMap 		= new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
        
        Long bagId = ((Number) tuple[aliasToIndexMap.get(BagDTO.ID_ALIAS)]).longValue();
 
        BagDTO bagDTO = bagDTOMap.computeIfAbsent(
             bagId,
             id -> {
                	BagDTO b = new BagDTO(tuple, aliasToIndexMap);
                	return b;
             }
        );
        
        Long bagItemId = ((Number) tuple[aliasToIndexMap.get(BagItemDTO.ID_ALIAS)]).longValue();
        
        BagItemDTO bagItemDTO = bagItemDTOMap.computeIfAbsent(
            bagItemId,
            id -> {
            	BagItemDTO bi = new BagItemDTO(tuple, aliasToIndexMap);
            	bagDTO.getBagItems().add(bi);
            	bi.setBag(bagDTO);
            	return bi;
            }
        );
        
        Long productId = ((Number) tuple[aliasToIndexMap.get(ProductDTO.ID_ALIAS)]).longValue();
        
        ProductDTO productDTO = productDTOMap.computeIfAbsent(
        	productId,
        	id -> {
        		ProductDTO p = new ProductDTO(tuple, aliasToIndexMap);
        		bagItemDTO.setProduct(p);
        		return p;
        	}
        );
        
        Long brandId = ((Number) tuple[aliasToIndexMap.get(BrandDTO.ID_ALIAS)]).longValue();
        
        brandDTOMap.computeIfAbsent(
        	brandId,
            id -> {
            	BrandDTO b = new BrandDTO(tuple, aliasToIndexMap);
            	productDTO.setBrand(b);
            	return b;
            }
        );
        
        Long departmentId = ((Number) tuple[aliasToIndexMap.get(DepartmentDTO.ID_ALIAS)]).longValue();
        
        departmentDTOMap.computeIfAbsent(
        	departmentId,
            id -> {
            	DepartmentDTO d = new DepartmentDTO(tuple, aliasToIndexMap);
            	productDTO.setDepartment(d);
            	return d;
            }
        );        
        
        Long customerId = ((Number) tuple[aliasToIndexMap.get(CustomerDTO.ID_ALIAS)]).longValue();
        
        customerDTOMap.computeIfAbsent(
            customerId,
            id -> {
            	CustomerDTO c = new CustomerDTO(tuple, aliasToIndexMap);
            	bagDTO.setCustomer(c);
            	return c;
            }
        );    
        
        return bagDTO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return new ArrayList<>(bagDTOMap.values());
	}
	
	public  Map<String, Integer> aliasToIndexMap(
	        String[] aliases) {
	     
	    Map<String, Integer> aliasToIndexMap = new LinkedHashMap<>();
	     
	    for (int i = 0; i < aliases.length; i++) {
	        aliasToIndexMap.put(aliases[i], i);
	    }
	     
	    return aliasToIndexMap;
	}

}
