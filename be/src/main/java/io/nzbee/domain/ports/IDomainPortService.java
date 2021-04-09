package io.nzbee.domain.ports;

public interface IDomainPortService<X> {

    void save(X domainObject);

	void update(X domainObject);
	
	void delete(X domainObject);
	
}
