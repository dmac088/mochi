package io.nzbee.domain.bag;

import io.nzbee.domain.IDomainService;

public interface IBagItemService extends IDomainService<BagItem> {

	void checkAllBagItemRules(BagItem object);
	
}
