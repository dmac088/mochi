package io.nzbee.entity.tag.view.facet;

import java.util.List;
import io.nzbee.entity.IDao;
import io.nzbee.entity.StringCollectionWrapper;

public interface ITagFacetDao  extends IDao<TagFacetDTO> {

	List<TagFacetDTO> findAll(String locale, String rootCategory);
	
	List<TagFacetDTO> findAll(String locale, String rootCategory, StringCollectionWrapper codes);
	
	List<TagFacetDTO> findAll(String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, Double maxPrice);

}
