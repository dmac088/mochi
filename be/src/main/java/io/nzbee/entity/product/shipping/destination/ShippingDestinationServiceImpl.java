package io.nzbee.entity.product.shipping.destination;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.nzbee.entity.StringCollectionWrapper;

@Service
public class ShippingDestinationServiceImpl implements IShippingDestinationService {

	@Autowired
	private IShippingDestinationRepository shippingDestinationRepository;
	
	@Autowired
	private IShippingDestinationDao shippingDestinationDao;
	
	@Override
	public List<ShippingDestinationDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingDestinationDTO> findAll(String locale, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingDestinationEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingDestinationDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingDestinationDTO> findByDesc(String locale, String desc) {
		return shippingDestinationRepository.findByDesc(locale, desc);
	}

	@Override
	public Optional<ShippingDestinationDTO> findByCode(String locale, String code) {
		return shippingDestinationRepository.findByCode(locale, code);
	}

	@Override
	public List<ShippingDestinationDTO> findAll(String locale, String currency, String rootCategory, StringCollectionWrapper codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ShippingDestinationEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ShippingDestinationEntity> findById(Long id) {
		return shippingDestinationRepository.findById(id);
	}

	@Override
	public Optional<ShippingDestinationEntity> findByCode(String code) {
		return shippingDestinationDao.findByCode(code);
	}

	@Override
	public void save(ShippingDestinationEntity t) {
		shippingDestinationRepository.save(t);
		
	}

	@Override
	public void update(ShippingDestinationEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ShippingDestinationEntity t) {
		// TODO Auto-generated method stub
		
	}

}
