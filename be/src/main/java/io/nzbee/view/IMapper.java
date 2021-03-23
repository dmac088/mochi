package io.nzbee.view;

//in, out, domain
public interface IMapper<D, E, F> {

	E toDto(F d);

	F toDo(D dto);


}
