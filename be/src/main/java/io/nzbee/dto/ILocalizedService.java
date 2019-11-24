package io.nzbee.dto;

import java.util.List;

public interface ILocalizedService<T, X, Y> {

	//for a DTO we need the locale
	T findById(String locale, String currency, long brandId);
	
	//for a DTO we need the locale
	T findByCode(String locale, String currency, String code);
	
	//for a DTO we need the locale
	T findByDesc(String locale, String currency, String desc);
	
	//for a DTO we need the locale
	List<T> findAll(String locale, String currency);
	
	List<T> findAll(String locale, String currency, List<String> codes);
	
	//for a DTO we need the locale
	T entityToDTO(String locale, String currency, X entity);

	T doToDto(Y dO);
}
