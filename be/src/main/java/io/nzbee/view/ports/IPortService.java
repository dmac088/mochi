package io.nzbee.view.ports;


public interface IPortService<X> {

    void save(X viewObject);

	void update(X viewObject);
	
	void delete(X viewObject);

	
}
