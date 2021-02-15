package io.nzbee.entity.product.shipping.destination.attribute;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

public class ShippingDestinationAttributeServiceImpl implements IShippingDestinationAttributeService {

	@Autowired
	private IShippingDestinationAttributeRepository shippingDestinationAttributeRepository;
	
	@Override
	public Optional<ShippingDestinationAttributeEntity> findByCode(String locale, String code) {
		return shippingDestinationAttributeRepository.findByLclCdAndShippingDestinationShippingDestinationCode(locale, code);
	}
	
	@Override
	public List<ShippingDestinationAttributeEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingDestinationAttributeEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingDestinationAttributeEntity> findByCode(String code) {
		return null;
	}

	@Override
	public void save(ShippingDestinationAttributeEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ShippingDestinationAttributeEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ShippingDestinationAttributeEntity t) {
		// TODO Auto-generated method stub
		
	}



}
