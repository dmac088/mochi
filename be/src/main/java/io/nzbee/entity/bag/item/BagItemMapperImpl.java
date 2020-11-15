package io.nzbee.entity.bag.item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.bag.IBagMapper;
import io.nzbee.entity.bag.status.BagItemStatus;
import io.nzbee.entity.bag.status.IBagItemStatusService;
import io.nzbee.entity.product.IProductMapper;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;

@Component
public class BagItemMapperImpl implements IBagItemMapper {

	@Autowired 
	private IBagMapper bagMapper;
	
	@Autowired
	private IProductMapper productMapper;
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IBagItemStatusService bagItemStatusService;


	@Override
	public BagItemEntity doToEntity(BagItem d) {
		Optional<ProductEntity> op = productService.findByCode(d.getProduct().getProductUPC());
		Optional<BagItemStatus> obis = bagItemStatusService.findByCode(Constants.bagStatusCodeNew);
		BagItemEntity bi = new BagItemEntity(op.get());
		bi.setBagItemStatus(obis.get());
		bi.setQuantity(d.getQuantity());	
		return bi;
	}

	@Override 
	public BagItem DTOToDo(BagItemDTO dto) {
		Bag b = bagMapper.DTOToDo(dto.getBag());
		Product p = productMapper.DTOToDo(dto.getProduct());
		return new BagItem(b, p, dto.getQuantity());
	} 

}
