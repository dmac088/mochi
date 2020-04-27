package io.nzbee.domain.product;

import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import io.nzbee.domain.IProductDimensionService;
import io.nzbee.dto.facet.IFacet;

public interface IProductService extends IProductDimensionService<Product> {


	Page<Product> findAll(	String locale, 
							String currency, 
							Double price, 
							Pageable pageable, 
							String categoryDesc,
							Set<IFacet> selectedFacets, 
							String sortBy);

	Page<Product> findAll(	String locale, 
							String currency, 
							Pageable pageable, 
							String categoryDesc, 
							Set<IFacet> facets,
							String sortBy);

	Page<Product> findAllByBrand(	String locale, 
									String currency, 
									Double price, 
									Pageable pageable, 
									String categoryDesc,
									String brandCode, 
									String sortBy);

}
