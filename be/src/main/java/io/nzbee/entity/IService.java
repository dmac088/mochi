package io.nzbee.entity;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

	 List<T> findAll();
	
	 Optional<T> findById(Long id);
	
	 Optional<T> findByCode(String code);
	
	 void save(T t);
	
	 void update(T t);
	
	 void delete(T t);

}
