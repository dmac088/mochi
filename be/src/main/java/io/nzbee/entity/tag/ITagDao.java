package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.IDao;
import io.nzbee.entity.StringCollectionWrapper;

public interface ITagDao  extends IDao<TagDTO> {

	Optional<TagEntity> findByCode(String code);

	Optional<TagDTO> findById(String locale, String rootCategory, Long id);
	
	Optional<TagDTO> findByCode(String locale, String rootCategory, String code);

	Optional<TagDTO> findByDesc(String locale, String rootCategory, String desc);

	List<TagDTO> findAll(String locale, String rootCategory);
	
	List<TagDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes);
	
	List<TagDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, Double maxPrice);

	Optional<TagEntity> findById(Long id);

	List<TagEntity> findAll();

	List<TagEntity> findAll(Set<String> codes);

	

}
