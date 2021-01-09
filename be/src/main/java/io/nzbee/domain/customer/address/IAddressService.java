package io.nzbee.domain.customer.address;

import io.nzbee.domain.IService;

public interface IAddressService extends IService<Address> {

	Address findByUsernameAndType(String userName, String addressTypeCode);
	
}
