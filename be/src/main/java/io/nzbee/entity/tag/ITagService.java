package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.ILightLocalizedService;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.ISearchDimensionService;

public interface ITagService  extends ILightLocalizedService<TagFacetViewDTO, TagEntity>, ISearchDimensionService<TagFacetViewDTO> {

	List<TagFacetViewDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, Double maxPrice);

	Optional<TagEntity> findByCode(String tagCode);

	Optional<TagFacetViewDTO> findByCode(String locale, String rootCategory, String code);

	Optional<TagFacetViewDTO> findByDesc(String locale, String rootCategory, String desc);

	List<TagFacetViewDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes);

	Optional<TagEntity> findById(Long id);

	List<TagEntity> findAll();

	
}
