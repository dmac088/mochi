package io.nzbee.entity.adapters.view;

import org.springframework.data.util.Streamable;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.resources.product.physical.full.PhysicalProductFullResource;
import io.nzbee.view.ports.IPhysicalProductFullPortService;
import io.nzbee.view.product.physical.PhysicalProductFullView;

public class PhysicalProductFullAdapterImpl implements IPhysicalProductFullPortService {


	@Override
	public RepresentationModelAssemblerSupport<PhysicalProductFullView, PhysicalProductFullResource> findAll(
			String locale, String currency, String categoryCode, StringCollectionWrapper categoryCodes,
			StringCollectionWrapper brandCodes, StringCollectionWrapper tagCodes, Double maxPrice, String page,
			String size, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Streamable<PhysicalProductFullView> findByCode(String locale, String currency, String code) {
		// TODO Auto-generated method stub
		return null;
	}

}
