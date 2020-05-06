package io.nzbee.domain.ports;

public interface IPortService<X> {

    void save(X domainObject);

	void update(X domainObject);
	
}
