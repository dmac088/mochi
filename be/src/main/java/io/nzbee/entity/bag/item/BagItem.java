package io.nzbee.entity.bag.item;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.nzbee.entity.bag.Bag;
import io.nzbee.entity.bag.status.BagItemStatus;
import io.nzbee.entity.product.Product;

@Entity
@Table(name = "bag_item", schema = "mochi")
public class BagItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bag_item_id")
	private Long bagItemId;

	@ManyToOne
	@JoinColumn(name="bag_id")
	private Bag bag;
	
	@ManyToOne
	@JoinColumn(name="prd_id")
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="bag_item_sts_id")
	private BagItemStatus bagItemStatus;
	
	@Column(name="qty")
	private int quantity;
	
	public Long getBagItemId() {
		return bagItemId;
	}
	
	public BagItem() {
	
	}
	
	public BagItem(Product p) {
		this.product = p;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Bag getBag() {
		return bag;
	}

	public void setBag(Bag bag) {
		this.bag = bag;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public BagItemStatus getBagItemStatus() {
		return bagItemStatus;
	}

	public void setBagItemStatus(BagItemStatus bagItemStatus) {
		this.bagItemStatus = bagItemStatus;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BagItem)) return false;
        return bagItemId != null && bagItemId.equals(((BagItem) o).getBagItemId());
    }
 
    @Override
    public int hashCode() {
    	return Objects.hash(this.getBagItemId());
    }
}
