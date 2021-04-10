package io.nzbee.view;


public interface IViewService<T> {

	void save(T object); 
	
	void delete(T object);
	
	void update(T object);

}
