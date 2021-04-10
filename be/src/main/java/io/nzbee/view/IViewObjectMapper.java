package io.nzbee.view;

//in, out, domain
public interface IViewObjectMapper<D, E, F> {

	E toView(F d);

}
