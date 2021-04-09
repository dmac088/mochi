package io.nzbee.entity;

public interface IService<T> {

	 void save(T t);
	
	 void update(T t);
	
	 void delete(T t);

}
