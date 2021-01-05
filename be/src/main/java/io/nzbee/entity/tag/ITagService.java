package io.nzbee.entity.tag;

import java.util.List;
import java.util.Optional;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.ISearchDimensionService;

public interface ITagService  extends ILocalizedService<TagDTO, TagEntity>, ISearchDimensionService<TagDTO> {

	List<TagDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, Double maxPrice);

	Optional<TagEntity> findByCode(String tagCode);

	
}
