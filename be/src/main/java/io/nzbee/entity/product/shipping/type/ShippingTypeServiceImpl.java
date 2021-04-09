package io.nzbee.entity.product.shipping.type;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;

@Service
public class ShippingTypeServiceImpl implements IShippingTypeService {

	@Autowired
	private IShippingTypeRepository shippingTypeRepository;
	
	@Override
	public List<ShippingTypeDTO> findAll(String locale) {
		return shippingTypeRepository.findAll(locale);
	}
	
	@Override
	public List<ShippingTypeDTO> findAll(String locale, String destinationCode) {
		return shippingTypeRepository.findAllByDestinationCode(locale, destinationCode);
	}
	
	@Override
	public List<ShippingTypeDTO> findAll(String locale, String destinationCode, Double bagWeight) {
		return shippingTypeRepository.findAllByDestinationCodeAndBagWeight(locale, destinationCode, bagWeight);
	}

	@Override
	public List<ShippingTypeDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingTypeEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingTypeDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingTypeDTO> findByDesc(String locale, String desc) {
		return shippingTypeRepository.findByDesc(locale, desc);
	}

	@Override
	public Optional<ShippingTypeDTO> findByCode(String locale, String code) {
		return shippingTypeRepository.findByCode(locale, code);
	}

	@Override
	public Optional<ShippingTypeEntity> findById(Long id) {
		return shippingTypeRepository.findById(id);
	}

	@Override
	public Optional<ShippingTypeEntity> findByCode(String code) {
		return shippingTypeRepository.findByShippingTypeCode(code);
	}

	@Override
	public void save(ShippingTypeEntity t) {
		shippingTypeRepository.save(t);
	}

	@Override
	public void update(ShippingTypeEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ShippingTypeEntity t) {
		// TODO Auto-generated method stub
		
	}


}
