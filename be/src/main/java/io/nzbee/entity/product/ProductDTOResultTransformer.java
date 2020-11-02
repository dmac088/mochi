package io.nzbee.entity.product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;
import io.nzbee.entity.brand.BrandDTO;
import io.nzbee.entity.category.product.CategoryProductDTO;
import io.nzbee.entity.product.department.DepartmentDTO;

public class ProductDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;
	
	private Map<Long, ProductDTO> productDTOMap = new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
        
        Long productId = ((Number) tuple[aliasToIndexMap.get(ProductDTO.ID_ALIAS)]).longValue();
 
        ProductDTO productDTO = productDTOMap.computeIfAbsent(
            productId,
            id -> new ProductDTO(tuple, aliasToIndexMap)
        );
        
        productDTO.setBrand(new BrandDTO(tuple, aliasToIndexMap));
        
        productDTO.setDepartment(new DepartmentDTO(tuple, aliasToIndexMap));
         
        productDTO.getCategories().add(
            new CategoryProductDTO(tuple, aliasToIndexMap)
        );
 
        return productDTO;
	}

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
