package io.nzbee.entity;


public interface IMapper<D, E> {

	D entityToDo(E e);
	
	D entityToDo(E e, String locale, String currency);
}
