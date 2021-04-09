package io.nzbee.domain.ports;

import java.util.List;
import java.util.Set;

public interface IProductDimensionService<X> extends IDomainPortService<X> {
	
	List<X> findAll(String locale);
	    
	List<X> findAll(String locale, Set<String> codes);

	X findByCode(String locale, String code);

	X findByDesc(String locale, String desc);

	    
}
