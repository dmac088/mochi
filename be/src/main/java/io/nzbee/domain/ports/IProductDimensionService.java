package io.nzbee.domain.ports;

import java.util.Set;

public interface IProductDimensionService<X> extends IPortService<X> {
	
	Set<X> findAll(String locale, String currency);
	    
	Set<X> findAll(String locale, String currency, Set<String> codes);

	X findByCode(String locale, String currency, String code);

	X findByDesc(String locale, String currency, String desc);
	    
}
