package io.nzbee.dto;

import java.util.List;
import java.util.Optional;

public interface IService<T, X, Y> {

	Optional<T> findById(long Id);
	
	//for a DTO we need the locale
	Optional<T> findByCode(String code);
	
	Optional<T> findByDesc(String desc);
	
	List<T> findAll();
	
	List<T> findAll(List<IDto> dtos);
	
	Optional<T> entityToDTO(X entity);

	Optional<T> doToDto(Y domainObject);
	
	void save(IDto dto);
}
