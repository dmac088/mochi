package io.nzbee.entity.bag.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.bag.IBagMapper;
import io.nzbee.entity.product.IProductMapper;

@Component
public class BagItemMapperImpl implements IBagItemMapper {

	@Autowired 
	private IBagMapper bagMapper;
	
	@Autowired
	private IProductMapper productMapper;
	
	@Override
	public BagItem entityToDo(io.nzbee.entity.bag.item.BagItem e) {
		Bag b = bagMapper.entityToDo(e.getBag());
		Product p = productMapper.entityToDo(e.getProduct());
		BagItem bi = new BagItem(b, p, e.getQuantity());
		return bi;
	}

	@Override
	public io.nzbee.entity.bag.item.BagItem doToEntity(BagItem d) {
		// TODO Auto-generated method stub
		return null;
	}

}
