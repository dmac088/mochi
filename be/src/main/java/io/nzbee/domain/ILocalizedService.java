package io.nzbee.domain;

import java.util.Set;

public interface ILocalizedService<T> extends IService<T> {

	T findByCode(String locale, String currency, String code);	
	
	T findByDesc(String locale, String currency, String desc); 

	Set<T> findAll(String locale, String currency);

	Set<T> findAll(String locale, String currency, Set<String> codes);

	
}
