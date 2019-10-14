package io.nzbee.dto;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

	//for a DTO we need the locale
	Optional<T> findById(long brandId);
	
	//for a DTO we need the locale
	Optional<T> findByCode(String code);
	
	//for a DTO we need the locale
	Optional<T> findByDesc(String desc);
	
	//for a DTO we need the locale
	List<T> findAll();
	
	List<T> findAll(List<String> codes);
	
	//for a DTO we need the locale
	T entityToDTO(Object entity);

	T doToDto(Object dO);
}
