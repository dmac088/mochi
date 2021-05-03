package io.nzbee.view.product.tag.facet;

import java.util.List;
import java.util.Set;

import io.nzbee.domain.ILocalizedService;

public interface ITagFacetService extends ILocalizedService<TagFacetView> {

	List<TagFacetView> findAll(String locale, String currency, String categoryCode, Set<String> collect, Set<String> collect2,
			Double maxPrice);

}
