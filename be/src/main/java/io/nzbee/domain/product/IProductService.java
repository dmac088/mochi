package io.nzbee.domain.product;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Streamable;

import io.nzbee.domain.IService;
import io.nzbee.dto.IDto;
import io.nzbee.resources.product.ProductResource;
import io.nzbee.ui.component.web.facet.IFacet;

public interface IProductService extends IService<Product> {

	List<Product> findAll(String locale, String currency, List<String> productCodes);

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
