package io.nzbee.entity.brand.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;

import io.nzbee.entity.brand.domain.BrandDomainDTO;


public class BrandDomainDTOResultTransformer implements ResultTransformer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<Long, BrandDomainDTO> brandDTOMap = new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
        
        Long brandId = ((Number) tuple[aliasToIndexMap.get(BrandDomainDTO.ID_ALIAS)]).longValue();
 
        BrandDomainDTO brandDTO = brandDTOMap.computeIfAbsent(
            brandId,
            id -> new BrandDomainDTO(tuple, aliasToIndexMap)
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