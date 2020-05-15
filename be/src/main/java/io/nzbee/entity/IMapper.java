package io.nzbee.entity;

public interface IMapper<D, E> {

	D entityToDo(E e);

	E doToEntity(D d);

}
