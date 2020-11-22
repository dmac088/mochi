package io.nzbee.entity.promotion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "promotion_bngnf", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prm_id")
@DiscriminatorValue("1")
public class PromotionBNGNF extends Promotion {


	@Column(name="buy_qty")
	private int buyQty;
	
	@Column(name="free_qty")
	private int freeQty;

	public int getBuyQty() {
		return buyQty;
	}

	public void setBuyQty(int buyQty) {
		this.buyQty = buyQty;
	}

	public int getFreeQty() {
		return freeQty;
	}

	public void setFreeQty(int freeQty) {
		this.freeQty = freeQty;
	}

}
