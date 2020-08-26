package io.nzbee.domain.bag;

import io.nzbee.domain.IService;

public interface IBagService extends IService<Bag> {

	Bag findByCode(String userName);

	Bag addItemToBag(BagItem bagItem);
	
}
