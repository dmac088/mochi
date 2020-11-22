package io.nzbee.entity.tag;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.ResultTransformer;

import io.nzbee.entity.tag.TagDTO;

public class TagDTOResultTransformer implements ResultTransformer {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<Long, TagDTO> tagDTOMap = new LinkedHashMap<>();
	
	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Integer> aliasToIndexMap = aliasToIndexMap(aliases);
		
        Long tagId = ((BigInteger) tuple[aliasToIndexMap.get(TagDTO.ID_ALIAS)]).longValue();
 
        TagDTO tagDTO = tagDTOMap.computeIfAbsent(
            tagId,
            id -> new TagDTO(tuple, aliasToIndexMap)
        );
        
        return tagDTO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return new ArrayList<>(tagDTOMap.values());
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
