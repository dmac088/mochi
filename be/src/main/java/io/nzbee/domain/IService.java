package io.nzbee.domain;

import java.util.List;
import java.util.Set;

public interface IService<T> {

	//for a DTO we need the locale
	T findByCode(String locale, String currency, String code);
	
	//for a DTO we need the locale
	T findByDesc(String locale, String currency, String desc);
	
	Set<T> findAll(String locale, String currency);
	
	Set<T> findAll(String locale, String currency, List<String> codes);
	
	String tokenToCode(String token);

	
}
