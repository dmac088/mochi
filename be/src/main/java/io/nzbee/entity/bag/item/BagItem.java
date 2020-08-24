package io.nzbee.entity.bag.item;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.nzbee.entity.bag.Bag;
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
	
	@Column(name="qty")
	private int quantity;
	
	public Long getBagItemId() {
		return bagItemId;
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
