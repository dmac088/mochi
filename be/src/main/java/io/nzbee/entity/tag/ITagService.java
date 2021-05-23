package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.ILightLocalizedService;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.tag.view.facet.TagFacetDTO;
import io.nzbee.search.ISearchDimensionService;

public interface ITagService  extends ILightLocalizedService<TagFacetDTO, TagEntity>, ISearchDimensionService<TagFacetDTO> {

	Optional<TagEntity> findByCode(String tagCode);

	Optional<TagFacetDTO> findByCode(String locale, String rootCategory, String code);

	Optional<TagFacetDTO> findByDesc(String locale, String rootCategory, String desc);

	List<TagFacetDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes);

	Optional<TagEntity> findById(Long id);

	List<TagEntity> findAll();

	
}
