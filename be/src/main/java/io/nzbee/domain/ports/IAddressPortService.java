package io.nzbee.domain.ports;

import io.nzbee.domain.customer.address.Address;

public interface IAddressPortService extends IPortService<Address> {

	Address findByUsernameAndType(String userName, String addressTypeCode);
	
}
