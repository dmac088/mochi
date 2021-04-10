package io.nzbee.domain.customer.address;

import io.nzbee.domain.IDomainService;

public interface IAddressService extends IDomainService<Address> {

	Address findByUsernameAndType(String userName, String addressTypeCode);
	
}
