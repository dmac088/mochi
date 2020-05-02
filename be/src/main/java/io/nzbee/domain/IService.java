package io.nzbee.domain;

public interface IService<T> {

	void save(T object); 
	
	void delete(T object);

}
