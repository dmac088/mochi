package io.nzbee.entity.bag.item;

import io.nzbee.entity.bag.BagDTO;
import io.nzbee.entity.product.ProductDTO;

public class BagItemDTO {
	
	public static final String ID_ALIAS = "bag_item_id";
	
    public static final String QUANTITY_ALIAS = "qty";

	private Long bagItemId;
	
	private BagDTO bag;
	
	private ProductDTO product;
	
	private BagItemStatusDTO bagItemStatus;
	
	private int quantity;

	public BagItemDTO(Long bagItemId) {
		this.bagItemId = bagItemId;
		
	}

	public Long getBagItemId() {
		return bagItemId;
	}

	public BagDTO getBag() {
		return bag;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public BagItemStatusDTO getBagItemStatus() {
		return bagItemStatus;
	}
	
}
