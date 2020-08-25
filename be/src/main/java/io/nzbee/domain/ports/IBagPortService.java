package io.nzbee.domain.ports;

import io.nzbee.domain.bag.Bag;
import io.nzbee.dto.bag.item.BagItemDTO;

public interface IBagPortService  extends IPortService<Bag> {

	public Bag addItemToBag(String userName, BagItemDTO bagItem);

	Bag findByCode(String userName);
	
}
