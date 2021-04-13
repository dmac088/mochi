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
		LOGGER.debug("call ShippingTypeServiceImpl.findAll() with parameters {}, {}, {}", locale, destinationCode, bagWeight);
		return shippingTypeRepository.findAllByDestinationCodeAndBagWeight(locale, destinationCode, bagWeight);
	}

	@Override
	public Optional<ShippingTypeDTO> findByCode(String locale, String code) {
		LOGGER.debug("call ShippingTypeServiceImpl.findByCode() with parameters {}, {}", locale, code);
		return shippingTypeRepository.findByCode(locale, code);
	}

	@Override
	public Optional<ShippingTypeEntity> findById(Long id) {
		LOGGER.debug("call ShippingTypeServiceImpl.findById() with parameters {}", id);
		return shippingTypeRepository.findById(id);
	}

	@Override
	public Optional<ShippingTypeEntity> findByCode(String code) {
		LOGGER.debug("call ShippingTypeServiceImpl.findByCode() with parameters {}", code);
		return shippingTypeRepository.findByShippingTypeCode(code);
	}

	@Override
	public void save(ShippingTypeEntity t) {
		LOGGER.debug("call ShippingTypeServiceImpl.save()");
		shippingTypeRepository.save(t);
	}

	@Override
	public void update(ShippingTypeEntity t) {
		LOGGER.debug("call ShippingTypeServiceImpl.update()");
		shippingTypeRepository.save(t);
	}

	@Override
	public void delete(ShippingTypeEntity t) {
		LOGGER.debug("call ShippingTypeServiceImpl.delete()");
		shippingTypeRepository.delete(t);
	}


}
