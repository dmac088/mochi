package io.nzbee.domain.tag;

import java.util.Set;

import io.nzbee.domain.ILocalizedService;

public interface ITagService extends ILocalizedService<Tag> {

	Set<Tag> findAll(String locale, String currency, String categoryCode, Set<String> collect, Set<String> collect2,
			Double maxPrice);

}
