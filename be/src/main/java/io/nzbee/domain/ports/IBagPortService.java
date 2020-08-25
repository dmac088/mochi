package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;

public interface IBagPortService  extends IPortService<Bag> {

	public Bag findByUsername(String userName);
	
}
