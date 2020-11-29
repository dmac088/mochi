package io.nzbee.entity.promotion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;
import io.nzbee.entity.promotion.PromotionDTO;

public class PromotionDTOResultTransformer implements ResultTransformer {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<Long, PromotionDTO> promotionDTOMap = new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
		
        Long promotionId = ((BigInteger) tuple[aliasToIndexMap.get(PromotionDTO.ID_ALIAS)]).longValue();
 
        PromotionDTO promotionDTO = promotionDTOMap.computeIfAbsent(
            promotionId,
            id -> new PromotionDTO(tuple, aliasToIndexMap)
        );
        
        return promotionDTO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return new ArrayList<>(promotionDTOMap.values());
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
