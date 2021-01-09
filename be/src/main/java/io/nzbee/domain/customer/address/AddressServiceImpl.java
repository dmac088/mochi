package io.nzbee.domain.customer.address;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.ports.IAddressPortService;

public class AddressServiceImpl implements IAddressService {

	@Autowired
	private IAddressPortService addressService;
	
	@Override
	public Address findByUsernameAndType(String userName, String addressTypeCode) {
		return addressService.findByUsernameAndType(userName, addressTypeCode);
	}
	
	@Override
	public void save(Address object) {
		addressService.save(object);
		
	}

	@Override
	public void delete(Address object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Address object) {
		// TODO Auto-generated method stub
		
	}

}
