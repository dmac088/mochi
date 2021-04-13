package io.nzbee.entity.adapters.view;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.entity.product.shipping.IShippingProductService;
import io.nzbee.entity.product.shipping.IShippingProductViewMapper;
import io.nzbee.entity.product.shipping.destination.IShippingDestinationService;
import io.nzbee.entity.product.shipping.destination.IShippingDestinationViewMapper;
import io.nzbee.entity.product.shipping.type.IShippingTypeService;
import io.nzbee.entity.product.shipping.type.IShippingTypeViewMapper;
import io.nzbee.view.ports.IShippingProductPortService;
import io.nzbee.view.product.shipping.ShippingProductView;
import io.nzbee.view.product.shipping.destination.ShippingDestinationView;
import io.nzbee.view.product.shipping.type.ShippingTypeView;

public class ShippingProductAdapterImpl  implements IShippingProductPortService {

	@Autowired
	private IShippingProductService productEntityService;
	
	@Autowired
	private IShippingDestinationService shippingDestinationService;
	
	@Autowired
	private IShippingTypeService shippingTypeService;
	
	@Autowired
	private IShippingDestinationViewMapper shippingDestinationMapper;
	
	@Autowired
	private IShippingTypeViewMapper shippingTypeMapper;
	
	@Autowired
	private IShippingProductViewMapper productMapper;
	
	
	@Override
	public List<ShippingDestinationView> findAllActiveByBagWeight(String locale, Double totalWeight) {
		return shippingDestinationService.findAllActiveByBagWeight(locale, totalWeight)
				.stream().map(sd -> shippingDestinationMapper.DTOToView(sd)).collect(Collectors.toList());
	}

	@Override
	public Optional<ShippingTypeView> findTypeByCode(String locale, String providerCode) {
		return Optional.ofNullable(shippingTypeMapper.DTOToView(shippingTypeService.findByCode(locale, providerCode).get()));
	}


	@Override
	public Optional<ShippingDestinationView> findDestinationByCode(String locale, String destinationCode) {
		return Optional.ofNullable(shippingDestinationMapper.DTOToView(shippingDestinationService.findByCode(locale, destinationCode).get()));
	}


	@Override
	public List<ShippingTypeView> findAllTypesByDestinationAndWeight(String locale, String destinationCode,
			Double totalWeight) {
		return shippingTypeService.findAll(locale, destinationCode, totalWeight)
				.stream().map(st -> shippingTypeMapper.DTOToView(st)).collect(Collectors.toList());
	}


	@Override
	public ShippingProductView findByDestinationAndTypeAndBagWeight(String locale, String currency, String code,
			String type, Double totalWeight) {
		return productMapper.DTOToView(productEntityService.findByDestinationAndTypeAndBagWeight(locale, currency, code, type, totalWeight).get());

	}


}
