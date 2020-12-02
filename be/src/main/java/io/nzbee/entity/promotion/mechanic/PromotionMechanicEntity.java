package io.nzbee.entity.promotion.mechanic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "promotion_mechanic", schema = "mochi")
public class PromotionMechanicEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prm_mec_id")
	private Long promotionMechanicId;
	
	@NaturalId
	@Column(name="prm_mec_cd", unique = true, updatable = false)
	private String promotionMechanicCode;
	
	@Column(name="prm_mec_desc")
	private String promotionMechanicDesc;

	public Long getPromotionMechanicId() {
		return promotionMechanicId;
	}

	public void setPromotionMechanicId(Long promotionMechanicId) {
		this.promotionMechanicId = promotionMechanicId;
	}

	public String getPromotionMechanicCode() {
		return promotionMechanicCode;
	}

	public void setPromotionMechanicCode(String promotionMechanicCode) {
		this.promotionMechanicCode = promotionMechanicCode;
	}

	public String getPromotionMechanicDesc() {
		return promotionMechanicDesc;
	}

	public void setPromotionMechanicDesc(String promotionMechanicDesc) {
		this.promotionMechanicDesc = promotionMechanicDesc;
	}
	
}
