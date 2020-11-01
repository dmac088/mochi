package io.nzbee.entity.bag.item;

import io.nzbee.entity.IMapper;
import io.nzbee.entity.bag.item.BagItemEntity;

public interface IBagItemMapper extends IMapper<io.nzbee.domain.bag.BagItem, BagItemEntity>{

	io.nzbee.domain.bag.BagItem entityToDo(String locale, String currency, BagItemEntity e);
	
}
