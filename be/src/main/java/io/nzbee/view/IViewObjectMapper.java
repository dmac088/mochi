package io.nzbee.view;

//in, out, domain
public interface IViewObjectMapper<DTO, VIEW> {

	VIEW toView(DTO d);

}
