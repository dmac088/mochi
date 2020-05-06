package io.nzbee.domain.ports;

import io.nzbee.domain.customer.Customer;

public interface IPortService<X> {

    void save(X domainObject);

	void update(X domainObject);
	
}
