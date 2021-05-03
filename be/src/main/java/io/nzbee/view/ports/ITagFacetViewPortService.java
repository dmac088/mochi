package io.nzbee.view.ports;

import java.util.List;
import java.util.Set;
import io.nzbee.view.product.tag.facet.TagFacetView;

public interface ITagFacetViewPortService {

	List<TagFacetView> findAll(String locale, String currency, String categoryCode, Set<String> categoryCodes,
			Set<String> brandCodes, Double maxPrice);

}
