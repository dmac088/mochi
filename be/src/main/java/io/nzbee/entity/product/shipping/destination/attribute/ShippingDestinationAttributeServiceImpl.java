package io.nzbee.entity.product.shipping.destination.attribute;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingDestinationAttributeServiceImpl implements IShippingDestinationAttributeService {

	@Autowired
	private IShippingDestinationAttributeRepository shippingDestinationAttributeRepository;
	
	@Override
	public Optional<ShippingDestinationAttributeEntity> findByCode(String locale, String code) {
		return shippingDestinationAttributeRepository.findByLclCdAndShippingDestinationShippingDestinationCode(locale, code);
	}
	
	@Override
	public void save(ShippingDestinationAttributeEntity t) {
		shippingDestinationAttributeRepository.save(t);
	}

	@Override
	public void update(ShippingDestinationAttributeEntity t) {
		shippingDestinationAttributeRepository.save(t);
	}

	@Override
	public void delete(ShippingDestinationAttributeEntity t) {
		shippingDestinationAttributeRepository.delete(t);
	}



}
