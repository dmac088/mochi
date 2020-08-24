package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;

public interface IBagPortService {

	public Bag findByUsername(String userName);
	
}
