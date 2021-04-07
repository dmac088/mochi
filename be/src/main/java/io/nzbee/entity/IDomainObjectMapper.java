package io.nzbee.entity;


public interface IDomainObjectMapper<D, E, DTO> {

	D DTOToDo(DTO dto);

	E doToEntity(D d);

}
