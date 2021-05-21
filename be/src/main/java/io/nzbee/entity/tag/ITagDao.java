package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.IDao;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.tag.view.facet.TagFacetDTO;

public interface ITagDao  extends IDao<TagFacetDTO> {

	Optional<TagEntity> findByCode(String code);

	Optional<TagFacetDTO> findById(String locale, String rootCategory, Long id);
	
	Optional<TagFacetDTO> findByCode(String locale, String rootCategory, String code);

	Optional<TagFacetDTO> findByDesc(String locale, String rootCategory, String desc);

	List<TagFacetDTO> findAll(String locale, String rootCategory);
	
	List<TagFacetDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes);
	
	List<TagFacetDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, Double maxPrice);

	Optional<TagEntity> findById(Long id);

	List<TagEntity> findAll();

	List<TagEntity> findAll(Set<String> codes);

	

}
