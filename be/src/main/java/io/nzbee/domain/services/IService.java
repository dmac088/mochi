package io.nzbee.domain.services;

import java.util.List;

//generic service class
public interface IService<T> {
	
	T findOne(Long id);
	
	T findOne(String code);
	
	List<T> findAll();
	
	T load();
	
	void save(T t);
	
	void update(T t);
	
	void delete(T t);
}
