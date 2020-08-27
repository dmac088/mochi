package io.nzbee.entity.bag.item;

import io.nzbee.entity.IMapper;
import io.nzbee.entity.bag.item.BagItem;

public interface IBagItemMapper extends IMapper<io.nzbee.domain.bag.BagItem, BagItem>{

	io.nzbee.domain.bag.BagItem entityToDo(String locale, String currency, BagItem e);
	
}
