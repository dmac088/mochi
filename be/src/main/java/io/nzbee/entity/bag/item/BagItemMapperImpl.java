package io.nzbee.entity.bag.item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.bag.IBagMapper;
import io.nzbee.entity.product.IProductMapper;
import io.nzbee.entity.product.IProductService;

@Component
public class BagItemMapperImpl implements IBagItemMapper {

	@Autowired 
	private IBagMapper bagMapper;
	
	@Autowired
	private IProductMapper productMapper;
	
	@Autowired
	private IProductService productService;
	
	@Override
	public BagItem entityToDo(String locale, String currency, io.nzbee.entity.bag.item.BagItem e) {
		Bag b = bagMapper.entityToDo(e.getBag());
		Product p = productMapper.entityToDo(productService.findByCode(locale, currency, e.getProduct().getProductUPC()).get());
		return new BagItem(b, p, e.getQuantity());
	}

	@Override
	public BagItem entityToDo(io.nzbee.entity.bag.item.BagItem e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public io.nzbee.entity.bag.item.BagItem doToEntity(BagItem d) {
		Optional<io.nzbee.entity.product.Product> op = productService.findByCode(d.getProduct().getProductUPC());
		io.nzbee.entity.bag.item.BagItem bi = new io.nzbee.entity.bag.item.BagItem(op.get());
		bi.setQuantity(d.getQuantity());	
		return bi;
	}

}
