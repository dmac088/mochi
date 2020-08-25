package io.nzbee.dto;


public interface IMapper<D, E> {

	D doToDto(E d);

	E dtoToDo(D dto);


}
