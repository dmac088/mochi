package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.IDao;
import io.nzbee.entity.StringCollectionWrapper;

public interface ITagDao  extends IDao<TagFacetViewDTO> {

	Optional<TagEntity> findByCode(String code);

	Optional<TagFacetViewDTO> findById(String locale, String rootCategory, Long id);
	
	Optional<TagFacetViewDTO> findByCode(String locale, String rootCategory, String code);

	Optional<TagFacetViewDTO> findByDesc(String locale, String rootCategory, String desc);

	List<TagFacetViewDTO> findAll(String locale, String rootCategory);
	
	List<TagFacetViewDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes);
	
	List<TagFacetViewDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, Double maxPrice);

	Optional<TagEntity> findById(Long id);

	List<TagEntity> findAll();

	List<TagEntity> findAll(Set<String> codes);

	

}
