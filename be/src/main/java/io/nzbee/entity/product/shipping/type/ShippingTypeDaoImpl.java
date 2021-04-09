package io.nzbee.entity.product.shipping.type;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import io.nzbee.entity.StringCollectionWrapper;

@Service
public class ShippingTypeDaoImpl implements IShippingTypeDao {

	@Override
	public Optional<ShippingTypeDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingTypeDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingTypeDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingTypeDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingTypeDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ShippingTypeEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ShippingTypeEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ShippingTypeEntity t) {
		// TODO Auto-generated method stub
		
	}

}
