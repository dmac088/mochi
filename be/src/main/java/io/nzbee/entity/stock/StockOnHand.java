package io.nzbee.entity.stock;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock_on_hand", schema = "mochi")
public class StockOnHand {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="soh_id")
	private Long stockOnHandId;
	
	@Column(name="soh_prd_id")
	private Long productId;
	
	@Column(name="soh_qty")
	private Long stockOnHand;

	public Long getStockOnHandId() {
		return stockOnHandId;
	}

	public void setStockOnHandId(Long stockOnHandId) {
		this.stockOnHandId = stockOnHandId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getStockOnHand() {
		return stockOnHand;
	}

	public void setStockOnHand(Long stockOnHand) {
		this.stockOnHand = stockOnHand;
	}
	
}
