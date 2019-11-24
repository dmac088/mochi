package io.nzbee.domain;

import java.util.List;
import java.util.Set;

public interface IService<T, X> {

	//for a DTO we need the locale
	T findById(String locale, String currency, Long Id);
	
	//for a DTO we need the locale
	T findByCode(String locale, String currency, String code);
	
	//for a DTO we need the locale
	T findByDesc(String locale, String currency, String desc);
	
	Set<T> findAll(String locale, String currency);
	
	Set<T> findAll(String locale, String currency, List<String> codes);
	
	Set<T> findAll(String locale, String currency, String categoryDesc, List<IDomainObject> lDo);
	
	T dtoToDO(X dto);
	
	String tokenToCode(String token);

	
}
