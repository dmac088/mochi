package io.nzbee.domain;

import java.util.Set;

public interface ILocalizedService<T> extends IService<T> {

	T findByCode(String locale, String code);	
	
	T findByDesc(String locale, String desc); 

	Set<T> findAll(String locale);

	Set<T> findAll(String locale, Set<String> codes);

	
}
