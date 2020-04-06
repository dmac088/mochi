package io.nzbee.entity.ports;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface IPortService<X> {

	/* converts from the infrastructure layer to the domain layer*/
	
    Optional<X> findByCode(String code);
    
    Set<X> findAll(String locale, String currency);
    
	X findByCode(String locale, String currency, String code);

	X findByDesc(String locale, String currency, String desc);

	Set<X> findAll(String locale, String currency, List<String> codes);
    
    void save(X domainObject);
	
}
