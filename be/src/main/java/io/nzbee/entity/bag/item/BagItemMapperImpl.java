package io.nzbee.entity.bag.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.bag.IBagMapper;
import io.nzbee.entity.bag.IBagService;
import io.nzbee.entity.product.IProductMapper;
import io.nzbee.entity.product.IProductService;
import java.util.Optional;

@Component
public class BagItemMapperImpl implements IBagItemMapper {

	@Autowired 
	private IBagMapper bagMapper;
	
	@Autowired
	private IProductMapper productMapper;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IBagService bagService;
	
	@Override
	public BagItem entityToDo(io.nzbee.entity.bag.item.BagItem e) {
		Bag b = bagMapper.entityToDo(e.getBag());
		Product p = productMapper.entityToDo(e.getProduct());
		return new BagItem(b, p, e.getQuantity());
	}

	@Override
	public io.nzbee.entity.bag.item.BagItem doToEntity(BagItem d) {
		Optional<io.nzbee.entity.product.Product> p = productService.findByCode(d.getProduct().getProductUPC());
		Optional<io.nzbee.entity.bag.Bag> b = bagService.findByCode(d.getBag().getCustomer().getUserName()); 
		io.nzbee.entity.bag.item.BagItem bi = new io.nzbee.entity.bag.item.BagItem(p.get());
		bi.setBag(b.get());
		return bi;
	}

}
