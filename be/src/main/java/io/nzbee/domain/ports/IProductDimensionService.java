package io.nzbee.domain.ports;

import java.util.Optional;
import java.util.Set;

public interface IProductDimensionService<X> extends IPortService<X> {
	
	Optional<X> findByCode(String code);
	
	Set<X> findAll(String locale, String currency);
	    
	Set<X> findAll(String locale, String currency, Set<String> codes);
	    
	Optional<X> findByCode(String locale, String currency, String code);

	Optional<X> findByDesc(String locale, String currency, String desc);
}
