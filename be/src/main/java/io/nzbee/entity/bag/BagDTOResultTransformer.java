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
	
	private Map<Long, BagDTO> bagDTOMap = new LinkedHashMap<>();
	
	private Map<Long, BagItemDTO> bagItemDTOMap = new LinkedHashMap<>();
	
	private Map<Long, ProductDTO> productDTOMap = new LinkedHashMap<>();
	
	private Map<Long, BrandDTO> brandDTOMap = new LinkedHashMap<>();
	
	private Map<Long, DepartmentDTO> departmentDTOMap = new LinkedHashMap<>();
	
	private Map<Long, CustomerDTO> customerDTOMap = new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
        
        Long bagId = ((Number) tuple[aliasToIndexMap.get(BagDTO.ID_ALIAS)]).longValue();
 
        BagDTO bagDTO = bagDTOMap.computeIfAbsent(
            bagId,
            id -> new BagDTO(tuple, aliasToIndexMap)
        );
        
        Long bagItemId = ((Number) tuple[aliasToIndexMap.get(BagItemDTO.ID_ALIAS)]).longValue();
        
        BagItemDTO bagItemDTO = bagItemDTOMap.computeIfAbsent(
            bagItemId,
            id -> new BagItemDTO(tuple, aliasToIndexMap)
        );
        
        Long productId = ((Number) tuple[aliasToIndexMap.get(ProductDTO.ID_ALIAS)]).longValue();
        
        ProductDTO productDTO = productDTOMap.computeIfAbsent(
        	productId,
        	id -> new ProductDTO(tuple, aliasToIndexMap)
        );
        
        Long brandId = ((Number) tuple[aliasToIndexMap.get(BrandDTO.ID_ALIAS)]).longValue();
        
        BrandDTO brandDTO = brandDTOMap.computeIfAbsent(
            brandId,
            id -> new BrandDTO(tuple, aliasToIndexMap)
        );
        
        Long departmentId = ((Number) tuple[aliasToIndexMap.get(DepartmentDTO.ID_ALIAS)]).longValue();
        
        DepartmentDTO departmentDTO = departmentDTOMap.computeIfAbsent(
            departmentId,
            id -> new DepartmentDTO(tuple, aliasToIndexMap)
        );        
        
        Long customerId = ((Number) tuple[aliasToIndexMap.get(CustomerDTO.ID_ALIAS)]).longValue();
        
        CustomerDTO customerDTO = customerDTOMap.computeIfAbsent(
            customerId,
            id -> new CustomerDTO(tuple, aliasToIndexMap)
        );    
        
        bagItemDTO.setBag(bagDTO);
        
        bagDTO.setCustomer(customerDTO);
        
        productDTO.setBrand(brandDTO);
        
        productDTO.setDepartment(departmentDTO);
        
        bagItemDTO.setProduct(productDTO);
        
        bagDTO.getBagItems().add(bagItemDTO);
        
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
