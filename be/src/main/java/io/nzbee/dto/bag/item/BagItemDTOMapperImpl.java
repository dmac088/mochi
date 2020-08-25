package io.nzbee.dto.bag.item;

import org.springframework.beans.factory.annotation.Autowired;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.bag.IBagService;
import io.nzbee.domain.product.IProductService;

public class BagItemDTOMapperImpl implements IBagItemDTOMapper {

	@Autowired
	private IBagService bagService;
	
	@Autowired
	private IProductService productService;

	@Override
	public BagItemDTO doToDto(BagItem d) {
		
		return null;
	}

	@Override
	public BagItem dtoToDo(BagItemDTO dto) {
		return new BagItem(
			//get the bag object
			bagService.findByCode(dto.getBagUserName()),
			
			//get the product object
			productService.findByCode(dto.getLocale(), dto.getItemUPC()),
			
			dto.getQty()
		);
	}

}
