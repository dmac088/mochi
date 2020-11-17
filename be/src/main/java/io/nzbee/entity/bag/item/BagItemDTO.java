package io.nzbee.entity.bag.item;

import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;
import io.nzbee.entity.bag.BagDTO;
import io.nzbee.entity.product.ProductDTO;

public class BagItemDTO {
	
	public static final String ID_ALIAS = "bag_item_id";
	
    public static final String QUANTITY_ALIAS = "qty";
    
    public static final String BAG_ITEM_STATUS_CODE_ALIAS = "bag_item_sts_cd";
    
    public static final String BAG_ITEM_STATUS_DESC_ALIAS = "bag_item_sts_desc";

	private Long bagItemId;
	
	private BagDTO bag;
	
	private ProductDTO product;
	
	private String bagItemStatusCode;
	
	private String bagItemStatusDesc; 
	
	private int quantity;

	public BagItemDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.bagItemId 			= ((BigInteger) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.bagItemStatusCode 	= tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_CODE_ALIAS)].toString();
		this.bagItemStatusDesc 	= tuple[aliasToIndexMap.get(BAG_ITEM_STATUS_DESC_ALIAS)].toString();
		this.quantity			= ((Short) tuple[aliasToIndexMap.get(QUANTITY_ALIAS)]).intValue();
	}

	public Long getBagItemId() {
		return bagItemId;
	}

	public BagDTO getBag() {
		return bag;
	}

	public void setBag(BagDTO bag) {
		this.bag = bag;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getBagItemStatusCode() {
		return bagItemStatusCode;
	}

	public String getBagItemStatusDesc() {
		return bagItemStatusDesc;
	}
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     BagItemDTO that = (BagItemDTO) o;
	     return this.getBagItemId() == that.getBagItemId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getBagItemId());
	}

}
