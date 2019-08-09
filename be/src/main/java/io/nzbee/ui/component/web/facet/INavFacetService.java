package io.nzbee.ui.component.web.facet;

import io.nzbee.domain.Brand;
import io.nzbee.domain.Category;
import io.nzbee.domain.Tag;

public interface INavFacetService {

	NavFacetResult findAll(String locale, String currency);

	NavFacetResult findAll(String locale, String currency, String category, NavFacetContainer selectedFacets);

	NavFacet<Category> convertCatToNavFacet(Category c);

	NavFacet<Tag> convertTagToNavFacet(Tag t);

	NavFacet<Brand> convertBrandToNavFacet(Brand b);
}
