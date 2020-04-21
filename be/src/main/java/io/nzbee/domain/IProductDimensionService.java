package io.nzbee.domain;

import java.util.Optional;
import java.util.Set;

public interface IProductDimensionService<T> extends IService<T> {

	Optional<T> findByCode(String locale, String currency, String code);
	
	Optional<T> findByDesc(String locale, String currency, String desc);
	
	Set<T> findAll(String locale, String currency);
	
	Set<T> findAll(String lcl, String currency, Set<String> codes);
	
	String tokenToCode(String token);
	
}
