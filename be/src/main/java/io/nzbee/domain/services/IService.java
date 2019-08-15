package io.nzbee.domain.services;

import java.util.List;

import io.nzbee.domain.Product;

//generic service class
public interface IService<T> {
	
	T findOne(Long id);
	
	T findOne(String code);
	
	List<T> findAll();
	
	T load();
	
	T save(T t);
	
	void update(T t);
	
	void delete(T t);

}
