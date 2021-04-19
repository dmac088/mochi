package io.nzbee.entity.category.product.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;

public class ProductProductCategoryViewDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;
	
	private Map<Long, ProductCategoryViewDTO> ProductCategoryViewDTOMap = new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
		
        Long tagId = ((Number) tuple[aliasToIndexMap.get(ProductCategoryViewDTO.ID_ALIAS)]).longValue();
        
        ProductCategoryViewDTO ProductCategoryViewDTO = ProductCategoryViewDTOMap.computeIfAbsent(
            tagId,
            id -> new ProductCategoryViewDTO(tuple, aliasToIndexMap)
        );
        
        return ProductCategoryViewDTO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return new ArrayList<>(ProductCategoryViewDTOMap.values());
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
