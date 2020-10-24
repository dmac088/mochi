package io.nzbee.entity.promotion.type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "promotion_type", schema = "mochi")
public class PromotionType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prm_typ_id")
	private Long promotionTypeId;
	
	@NaturalId
	@Column(name="prm_typ_cd", unique = true, updatable = false)
	private String promotionTypeCode;
		
	@Column(name="prm_typ_desc", unique = true, updatable = false)
	private String promotionTypeDesc;

	public Long getPromotionTypeId() {
		return promotionTypeId;
	}

	public void setPromotionTypeId(Long promotionTypeId) {
		this.promotionTypeId = promotionTypeId;
	}

	public String getPromotionTypeCode() {
		return promotionTypeCode;
	}

	public void setPromotionTypeCode(String promotionTypeCode) {
		this.promotionTypeCode = promotionTypeCode;
	}

	public String getPromotionTypeDesc() {
		return promotionTypeDesc;
	}

	public void setPromotionTypeDesc(String promotionTypeDesc) {
		this.promotionTypeDesc = promotionTypeDesc;
	}
	
	
}
