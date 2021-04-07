package io.nzbee.entity;


public interface IViewObjectMapper<V, E, DTO> {

	V DTOToView(DTO dto);

	E viewToEntity(V d);

}
