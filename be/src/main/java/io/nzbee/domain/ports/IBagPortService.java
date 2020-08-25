package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;

public interface IBagPortService  extends IPortService<Bag> {

	Bag findByCode(String userName);

	Bag addItemToBag(String userName, BagItem bagItem);

	void save(String userName, Bag domainObject);
	
}
