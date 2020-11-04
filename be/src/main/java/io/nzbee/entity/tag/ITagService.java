package io.nzbee.entity.tag;

import java.util.Optional;
import java.util.Set;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.search.ISearchDimensionService;

public interface ITagService  extends ILocalizedService<TagDTO, TagEntity>, ISearchDimensionService<TagDTO> {

	Set<TagDTO> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Double maxPrice);

	Optional<TagEntity> findByCode(String tagCode);

	
}
