package io.nzbee.entity.promotion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "promotion_bngnpct", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prm_id")
@DiscriminatorValue("1")
public class PromotionBNGNPCTEntity extends PromotionEntity {

	@Column(name="buy_qty")
	private int buyQty;
	
	@Column(name="pct_disc")
	private Double pctDisc;

	public int getBuyQty() {
		return buyQty;
	}

	public void setBuyQty(int buyQty) {
		this.buyQty = buyQty;
	}

	public Double getPctDisc() {
		return pctDisc;
	}

	public void setPctDisc(Double pctDisc) {
		this.pctDisc = pctDisc;
	}

}