package io.nzbee.dto;

import java.util.List;
import java.util.Optional;


public interface IService<T> {

	//for a DTO we need the locale
	Optional<T> findById(String locale, long brandId);
	
	//for a DTO we need the locale
	Optional<T> findByCode(String locale, String code);
	
	//for a DTO we need the locale
	Optional<T> findByDesc(String locale, String desc);
	
	//for a DTO we need the locale
	List<T> findAll(String locale, String currency);
	
	//for a DTO we need the locale
	T entityToDTO(String locale, Object entity);
}
