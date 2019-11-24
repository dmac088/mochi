package io.nzbee.dto;

import java.util.List;

public interface IService<T, X, Y> {

	T findById(long Id);
	
	//for a DTO we need the locale
	T findByCode(String code);
	
	T findByDesc(String desc);
	
	List<T> findAll();
	
	List<T> findAll(List<IDto> dtos);
	
	T entityToDTO(X entity);

	T doToDto(Y domainObject);
}
