package io.nzbee.entity.product.shipping.type;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingTypeServiceImpl implements IShippingTypeService {

	private final Logger LOGGER = LoggerFactory.getLogger(ShippingTypeServiceImpl.class);
	
	@Autowired
	private IShippingTypeRepository shippingTypeRepository;
	
	@Override
	public List<ShippingTypeDTO> findAll(String locale, String destinationCode) {
		return shippingTypeRepository.findAllByDestinationCode(locale, destinationCode);
	}
	
	@Override
	public List<ShippingTypeDTO> findAll(String locale, String destinationCode, Double bagWeight) {
		return shippingTypeRepository.findAllByDestinationCodeAndBagWeight(locale, destinationCode, bagWeight);
	}

	@Override
	public Optional<ShippingTypeDTO> findByDesc(String locale, String desc) {
		return shippingTypeRepository.findByDesc(locale, desc);
	}

	@Override
	public Optional<ShippingTypeDTO> findByCode(String locale, String code) {
		LOGGER.debug("call ShippingTypeServiceImpl.findByCode() with parameters {}, {}", locale, code);
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
		shippingTypeRepository.save(t);
	}

	@Override
	public void delete(ShippingTypeEntity t) {
		shippingTypeRepository.delete(t);
	}


}
