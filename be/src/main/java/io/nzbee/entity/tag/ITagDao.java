package io.nzbee.entity.tag;

import java.util.List;
import java.util.Set;
import io.nzbee.entity.ILocalizedDao;

public interface ITagDao  extends ILocalizedDao<Tag> {

	List<Tag> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Double maxPrice);

}
