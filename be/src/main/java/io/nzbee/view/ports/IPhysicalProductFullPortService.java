package io.nzbee.view.ports;

import org.springframework.data.util.Streamable;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.resources.product.physical.full.PhysicalProductFullResource;
import io.nzbee.view.product.physical.PhysicalProductFullView;

public interface IPhysicalProductFullPortService extends IViewPortService<PhysicalProductFullView> {
 

	RepresentationModelAssemblerSupport<PhysicalProductFullView, PhysicalProductFullResource> findAll(String locale, String currency,
			String categoryCode, StringCollectionWrapper categoryCodes, StringCollectionWrapper brandCodes,
			StringCollectionWrapper tagCodes, Double maxPrice, String page, String size, String sort);

	Streamable<PhysicalProductFullView> findByCode(String locale, String currency, String code);
	
	
}
