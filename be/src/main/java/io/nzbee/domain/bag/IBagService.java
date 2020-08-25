package io.nzbee.domain.bag;

import io.nzbee.domain.IService;
import io.nzbee.dto.bag.item.BagItemDTO;

public interface IBagService extends IService<Bag> {

	Bag findByCode(String userName);

	Bag addItemToBag(String userName, BagItemDTO bagItem);
	
}
