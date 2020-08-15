package io.nzbee.entity.tag;

import java.util.List;
import java.util.Set;
import io.nzbee.entity.ILocalizedService;
import io.nzbee.search.ISearchDimensionService;

public interface ITagService  extends ILocalizedService<Tag>, ISearchDimensionService<Tag> {

	List<Tag> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Double maxPrice);

	
}
