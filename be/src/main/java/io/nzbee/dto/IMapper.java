package io.nzbee.dto;

//in, out, domain
public interface IMapper<D, E, F> {

	E doToDto(F d);

	F dtoToDo(D dto);


}
