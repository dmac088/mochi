package io.nzbee.domain;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

	//for a DTO we need the locale
	Optional<T> findById(String locale, String currency, Long Id);
	
	//for a DTO we need the locale
	Optional<T> findByCode(String locale, String currency, String code);
	
	//for a DTO we need the locale
	Optional<T> findByDesc(String locale, String currency, String desc);
	
	//for a DTO we need the locale
	List<T> findAll(String locale, String currency);
	
	T dtoToDO(Object dto);

	T doToDto(Object dO);
	
}
