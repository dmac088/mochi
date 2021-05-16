package io.nzbee.entity.bag.item;

import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.entity.IDomainObjectMapper;

public interface IBagItemMapper extends IDomainObjectMapper<BagItem, BagItemEntity, BagItemDTO>{

	BagItem DTOToDo(Bag bDo, BagItemDTO dto);
	
}
