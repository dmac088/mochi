package io.nzbee.entity.promotion;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "promotion_pctmkd", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prm_id")
@DiscriminatorValue("2")
public class PromotionPCTMKDEntity extends PromotionEntity {

	@Column(name="mkd_pct")
	private int mkdPct;

	public int getMkdPct() {
		return mkdPct;
	}

	public void setMkdPct(int mkdPct) {
		this.mkdPct = mkdPct;
	}
	
}
