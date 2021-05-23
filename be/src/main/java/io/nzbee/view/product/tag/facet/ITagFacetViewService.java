package io.nzbee.view.product.tag.facet;

import java.util.List;
import java.util.Set;
import io.nzbee.view.IViewService;

public interface ITagFacetViewService extends IViewService<TagFacetView> {

	List<TagFacetView> findAll(String locale, String currency, String categoryCode, Set<String> collect, Set<String> collect2,
			Double maxPrice);

}
