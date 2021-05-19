package io.nzbee.entity.category.product.view.facet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;

public class ProductProductCategoryFacetDTOResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = 1L;
	
	private Map<Long, ProductCategoryFacetDTO> ProductCategoryViewDTOMap = new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
		
        Long tagId = ((Number) tuple[aliasToIndexMap.get(ProductCategoryFacetDTO.ID_ALIAS)]).longValue();
        
        ProductCategoryFacetDTO ProductCategoryViewDTO = ProductCategoryViewDTOMap.computeIfAbsent(
            tagId,
            id -> new ProductCategoryFacetDTO(tuple, aliasToIndexMap)
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
