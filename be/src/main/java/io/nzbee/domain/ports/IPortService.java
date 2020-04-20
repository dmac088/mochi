package io.nzbee.domain.ports;


public interface IPortService<X> {

    void save(X domainObject);
	
}
