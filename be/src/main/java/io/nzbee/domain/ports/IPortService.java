package io.nzbee.domain.ports;

import java.util.Optional;
import java.util.Set;

public interface IPortService<X> {

	/* converts from the infrastructure layer to the domain layer*/
	
    Optional<X> findByCode(String code);
    
    Set<X> findAll(String locale, String currency);
    
    Set<X> findAll(String locale, String currency, Set<String> codes);
    
	X findByCode(String locale, String currency, String code);

	X findByDesc(String locale, String currency, String desc);
	
    void save(X domainObject);
	
}
