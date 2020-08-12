package io.nzbee.domain.ports;

import java.util.Set;

public interface IProductDimensionService<X> extends IPortService<X> {
	
	Set<X> findAll(String locale);
	    
	Set<X> findAll(String locale, Set<String> codes);

	X findByCode(String locale, String code);

	X findByDesc(String locale, String desc);
	    
}
