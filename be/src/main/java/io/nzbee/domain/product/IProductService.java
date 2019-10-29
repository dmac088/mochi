package io.nzbee.domain.product;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.domain.IService;
import io.nzbee.ui.component.web.facet.Facet;

public interface IProductService extends IService<Product> {

	List<Product> findAll(String locale, String currency, List<String> productCodes);

	Page<Product> findAll(	String locale, 
							String currency, 
							Double price, 
							Pageable pageable, 
							String categoryDesc,
							List<Facet> facets, 
							String sortBy);

	Page<Product> findAll(	String locale, 
							String currency, 
							Pageable pageable, 
							String categoryDesc, 
							List<Facet> facets,
							String sortBy);

}
