package io.nzbee.entity.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import io.nzbee.entity.product.physical.PhysicalProductEntity;

@Entity
@Table(name = "stock_on_hand", schema = "mochi")
public class StockOnHandEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="soh_id")
	private Long stockOnHandId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="soh_prd_id")
	private PhysicalProductEntity product;
	
	@Column(name="soh_qty")
	private Long stockOnHand;

	public Long getStockOnHandId() {
		return stockOnHandId;
	}

	public void setStockOnHandId(Long stockOnHandId) {
		this.stockOnHandId = stockOnHandId;
	}

	public Long getStockOnHand() {
		return stockOnHand;
	}

	public void setStockOnHand(Long stockOnHand) {
		this.stockOnHand = stockOnHand;
	}

	public PhysicalProductEntity getProduct() {
		return product;
	}

	public void setProduct(PhysicalProductEntity product) {
		this.product = product;
	}
	
}
