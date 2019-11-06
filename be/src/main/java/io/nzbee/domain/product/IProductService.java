package io.nzbee.domain.product;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.domain.IService;
import io.nzbee.ui.component.web.facet.IFacet;

public interface IProductService extends IService<Product> {

	Set<Product> findAll(String locale, String currency, List<String> productCodes);

	Page<Product> findAll(	String locale, 
							String currency, 
							Double price, 
							Pageable pageable, 
							String categoryDesc,
							List<IFacet> facets, 
							String sortBy);

	Page<Product> findAll(	String locale, 
							String currency, 
							Pageable pageable, 
							String categoryDesc, 
							List<IFacet> facets,
							String sortBy);

}
