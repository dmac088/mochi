package io.nzbee.domain.product;

import java.util.Set;
import org.springframework.data.domain.Page;
import io.nzbee.domain.ILocalizedService;
import io.nzbee.search.dto.facet.IFacet;

public interface IProductService extends ILocalizedService<Product> {


	Page<Product> findAll(String locale, String currency, String categoryCode, String page, String size, String sortBy,
			Set<IFacet> selectedFacets);


}
