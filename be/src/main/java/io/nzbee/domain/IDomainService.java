package io.nzbee.domain;


public interface IDomainService<T> {

	void save(T object); 
	
	void delete(T object);
	
	void update(T object);

}
