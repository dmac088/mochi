package io.nzbee.entity.product.shipping.type.attribute;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingTypeAttributeServiceImpl implements IShippingTypeAttributeService {

	@Autowired
	private IShippingTypeAttributeRepository shippingTypeAttributeRepository;

	@Override
	public void save(ShippingTypeAttributeEntity t) {
		shippingTypeAttributeRepository.save(t);
	}

	@Override
	public void update(ShippingTypeAttributeEntity t) {
		shippingTypeAttributeRepository.save(t);
		
	}

	@Override
	public void delete(ShippingTypeAttributeEntity t) {
		shippingTypeAttributeRepository.delete(t);
	}

	@Override
	public Optional<ShippingTypeAttributeEntity> findByCode(String locale, String code) {
		return shippingTypeAttributeRepository.findByLclCdAndShippingTypeShippingTypeCode(locale, code);
	}

}
