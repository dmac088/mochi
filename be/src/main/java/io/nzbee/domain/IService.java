package io.nzbee.domain;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IService<T> {

	//for a DTO we need the locale
	Optional<T> findById(String locale, String currency, Long Id);
	
	//for a DTO we need the locale
	Optional<T> findByCode(String locale, String currency, String code);
	
	//for a DTO we need the locale
	Optional<T> findByDesc(String locale, String currency, String desc);
	
	Set<T> findAll(String locale, String currency);
	
	Set<T> findAll(String locale, String currency, List<String> codes);
	
	Set<T> findAll(String locale, String currency, String categoryDesc, List<IDomainObject> lDo);
	
	T dtoToDO(Object dto);
	
	String tokenToCode(String token);

	
}
