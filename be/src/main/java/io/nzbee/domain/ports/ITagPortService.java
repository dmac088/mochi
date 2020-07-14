package io.nzbee.domain.ports;

import java.util.Set;

import io.nzbee.domain.tag.Tag;

public interface ITagPortService extends IProductDimensionService<Tag> {

	Set<Tag> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Double maxPrice);

}
