package io.nzbee.view.bag.item;

import io.nzbee.domain.bag.BagItem;

public class BagItemDTOMapperImpl implements IBagItemDTOMapper {

	@Override
	public BagItemDTOOut doToDto(BagItem d) {
		BagItemDTOOut bi = new BagItemDTOOut();
		bi.setLocale(d.getLocale());
		bi.setCurrency(d.getCurrency());
		bi.setItemUPC(d.getProduct().getProductUPC());
		bi.setItemDesc(d.getProduct().getProductDesc());
		bi.setItemQty(d.getQuantity());
		bi.setMarkdownPrice(d.getProduct().getProductMarkdown());
		bi.setBagItemTotal(d.getBagItemTotal());
		return bi;
	}

	@Override
	public BagItem dtoToDo(BagItemDTOIn dto) {
		return null;
		
	}

}
