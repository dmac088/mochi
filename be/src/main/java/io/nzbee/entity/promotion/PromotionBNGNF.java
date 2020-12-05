package io.nzbee.entity.promotion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "promotion_bngnf", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prm_id")
@DiscriminatorValue("2")
public class PromotionBNGNF extends PromotionEntity {

	@Column(name="buy_qty")
	private int buyQty;
	
	@Column(name="free_qty")
	private Double freeQty;

	public int getBuyQty() {
		return buyQty;
	}

	public void setBuyQty(int buyQty) {
		this.buyQty = buyQty;
	}

	public Double getFreeQty() {
		return freeQty;
	}

	public void setFreeQty(Double freeQty) {
		this.freeQty = freeQty;
	}

}
