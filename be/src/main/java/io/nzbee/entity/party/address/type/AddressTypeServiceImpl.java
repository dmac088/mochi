package io.nzbee.entity.party.address.type;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

public class AddressTypeServiceImpl implements IAddressTypeService {

	@Autowired
	private IAddressTypeEntityRepository addressTypeRepository;
	
	@Override
	public Optional<AddressTypeEntity> findById(long id) {
		return addressTypeRepository.findById(id);
	}

	@Override
	public Optional<AddressTypeEntity> findByCode(String code) {
		return addressTypeRepository.findByAddressTypeCode(code);
	}

	@Override
	public List<AddressTypeEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddressTypeEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(AddressTypeEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(AddressTypeEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(AddressTypeEntity t) {
		// TODO Auto-generated method stub
		
	}

}
