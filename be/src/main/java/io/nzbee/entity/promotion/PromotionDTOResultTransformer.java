package io.nzbee.entity.promotion;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.transform.ResultTransformer;
import io.nzbee.entity.promotion.PromotionDTO;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicDTO;

public class PromotionDTOResultTransformer implements ResultTransformer {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<Long, PromotionDTO> promotionDTOMap = new LinkedHashMap<>();
	
	private Map<Long, PromotionMechanicDTO> promotionMechanicDTOMap = new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
		
        Long promotionId = ((BigInteger) tuple[aliasToIndexMap.get(PromotionDTO.ID_ALIAS)]).longValue();
        
        Long promotionMechanicId = ((BigInteger) tuple[aliasToIndexMap.get(PromotionMechanicDTO.ID_ALIAS)]).longValue();
 
        PromotionDTO promotionDTO = promotionDTOMap.computeIfAbsent(
            promotionId,
            id -> new PromotionBNGNPCTDTO(tuple, aliasToIndexMap)
        );
        
        PromotionMechanicDTO promotionMechanicDTO = promotionMechanicDTOMap.computeIfAbsent(
        	promotionMechanicId,
        	id -> new PromotionMechanicDTO(tuple, aliasToIndexMap)
        );
        
        promotionDTO.setMechanicDTO(promotionMechanicDTO);
        
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
