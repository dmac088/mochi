package io.nzbee.entity.category;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;

import io.nzbee.entity.category.brand.CategoryBrandDTO;
import io.nzbee.entity.category.product.CategoryProductDTO;


public class CategoryDTOResultTransformer implements ResultTransformer {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<Long, CategoryDTO> CategoryDTOMap = new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
		
        Long tagId = ((BigInteger) tuple[aliasToIndexMap.get(CategoryDTO.ID_ALIAS)]).longValue();
        
        String type = tuple[aliasToIndexMap.get(CategoryDTO.CATEGORY_TYPE_ALIAS)].toString();
 
        CategoryDTO categoryDTO = CategoryDTOMap.computeIfAbsent(
            tagId,
            id -> (type.equals("PRD01") 
            	  ? new CategoryProductDTO(tuple, aliasToIndexMap) 
            	  : new CategoryBrandDTO(tuple, aliasToIndexMap))
        );
        
        return categoryDTO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return new ArrayList<>(CategoryDTOMap.values());
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
