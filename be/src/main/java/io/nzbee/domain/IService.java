package io.nzbee.domain;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

	//for a DTO we need the locale
	Optional<T> findById(Long Id);
	
	//for a DTO we need the locale
	Optional<T> findByCode(String code);
	
	//for a DTO we need the locale
	Optional<T> findByDesc(String locale, String currency, String desc);
	
	//for a DTO we need the locale
	List<T> findAll(String locale, String currency);
	
	T dTOToDO(Object dTO);
	
}
