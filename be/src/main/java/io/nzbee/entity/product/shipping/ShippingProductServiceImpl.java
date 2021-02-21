package io.nzbee.entity.product.shipping;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;

@Service
public class ShippingProductServiceImpl implements IShippingProductService {

	@Override
	public List<ShippingProductEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingProductEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingProductEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ShippingProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ShippingProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ShippingProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ShippingProductDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingProductDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingProductEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingProductDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingProductDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingProductDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingProductDTO> findAll(String locale, String currency, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

}
