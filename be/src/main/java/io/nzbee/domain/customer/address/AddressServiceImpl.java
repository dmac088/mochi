package io.nzbee.domain.customer.address;

import org.springframework.beans.factory.annotation.Autowired;

import io.nzbee.domain.ports.IAddressPortService;

public class AddressServiceImpl implements IAddressService {

	@Autowired
	private IAddressPortService addressService;
	
	@Override
	public Address findByUsername(String userName) {
		return addressService.findByUsername(userName);
	}
	
	@Override
	public void save(Address object) {
		// TODO Auto-generated method stub
		
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
