package io.nzbee.entity.product.shipping.type.attribute;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingTypeAttributeServiceImpl implements IShippingTypeAttributeService {

	@Autowired
	private IShippingTypeAttributeRepository shippingTypeAttributeRepository;
	
	@Override
	public List<ShippingTypeAttributeEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingTypeAttributeEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingTypeAttributeEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ShippingTypeAttributeEntity t) {
		shippingTypeAttributeRepository.save(t);
	}

	@Override
	public void update(ShippingTypeAttributeEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ShippingTypeAttributeEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<ShippingTypeAttributeEntity> findByCode(String locale, String code) {
		return shippingTypeAttributeRepository.findByLclCdAndShippingTypeShippingTypeCode(locale, code);
	}

}
