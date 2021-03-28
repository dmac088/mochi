package io.nzbee.entity.promotion.level;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "promotion_level", schema = "mochi")
public class PromotionLevelEntity implements Serializable {

	private static final long serialVersionUID = -6852695326648001491L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prm_lvl_id")
	private Long promotionLevelId;
	
	@NaturalId
	@Column(name="prm_lvl_cd", unique = true, updatable = false)
	private String promotionLevelCode;
	
	@Column(name="prm_lvl_desc")
	private String promotionLevelDesc;

	public Long getPromotionLevelId() {
		return promotionLevelId;
	}

	public void setPromotionLevelId(Long promotionLevelId) {
		this.promotionLevelId = promotionLevelId;
	}

	public String getPromotionLevelCode() {
		return promotionLevelCode;
	}

	public void setPromotionLevelCode(String promotionLevelCode) {
		this.promotionLevelCode = promotionLevelCode;
	}

	public String getPromotionLevelDesc() {
		return promotionLevelDesc;
	}

	public void setPromotionLevelDesc(String promotionLevelDesc) {
		this.promotionLevelDesc = promotionLevelDesc;
	}

}
