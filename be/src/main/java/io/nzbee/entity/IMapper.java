package io.nzbee.entity;


public interface IMapper<D, E, DTO> {

	D DTOToDo(DTO dto);

	E doToEntity(D d);

}
