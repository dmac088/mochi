package io.nzbee.entity.tag.view.facet;

import java.util.List;
import io.nzbee.entity.IService;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.search.ISearchDimensionService;

public interface ITagFacetDTOService  extends IService<TagFacetDTO>, ISearchDimensionService<TagFacetDTO> {

	List<TagFacetDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, Double maxPrice);

	List<TagFacetDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes);
	
}
