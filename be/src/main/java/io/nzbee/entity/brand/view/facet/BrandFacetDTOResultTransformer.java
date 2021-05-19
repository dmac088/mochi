package io.nzbee.entity.brand.view.facet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;


public class BrandFacetDTOResultTransformer implements ResultTransformer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, BrandFacetDTO> brandDTOMap = new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
        
        String brandCode = ((String) tuple[aliasToIndexMap.get(BrandFacetDTO.CODE_ALIAS)]);
 
        BrandFacetDTO brandDTO = brandDTOMap.computeIfAbsent(
        	brandCode,
        	code -> new BrandFacetDTO(tuple, aliasToIndexMap)
        );
        
        return brandDTO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return new ArrayList<>(brandDTOMap.values());
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
