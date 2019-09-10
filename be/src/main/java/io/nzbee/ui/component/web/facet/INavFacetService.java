package io.nzbee.ui.component.web.facet;

import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.tag.Tag;

public interface INavFacetService {

	NavFacetResult findAll(String locale, String currency);

	NavFacetResult findAll(String locale, String currency, String category, NavFacetContainer selectedFacets);

	NavFacet<Category> convertCatToNavFacet(Category c);

	NavFacet<Tag> convertTagToNavFacet(Tag t);

	NavFacet<Brand> convertBrandToNavFacet(Brand b);

	String calcFacetId(String className, String id);

	String calcToken(String className, String id);

	NavFacetResult findAllBrands(String locale, String category);
}
