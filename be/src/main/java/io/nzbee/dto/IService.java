package io.nzbee.dto;

import java.util.List;
import java.util.Optional;


public interface IService<T> {

	Optional<T> findById(long brandId);
	
	Optional<T> findByCode(String code);
	
	Optional<T> findByDesc(String locale, String desc);
	
	List<T> findAll(String locale, String currency);

	void entityToDTO(String locale, T object);
}
