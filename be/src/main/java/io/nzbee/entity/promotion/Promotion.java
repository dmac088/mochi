package io.nzbee.entity.promotion;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

import io.nzbee.entity.promotion.mechanic.PromotionMechanic;
import io.nzbee.entity.promotion.type.PromotionType;

@Entity
@Table(name = "promotion", schema = "mochi")
public class Promotion {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prm_id")
	private Long promotionId;
	
	@NaturalId
	@Column(name="prm_cd", unique = true, updatable = false)
	private String promotionCode;
	
	@Column(name="prm_sht_desc")
	private String promotionShortDesc;
	
	@Column(name="prm_lng_desc")
	private String promotionLongDesc;
	
	@Column(name="prm_st_dt")
	private LocalDateTime promotionStartDate;
	
	@Column(name="prm_en_dt")
	private LocalDateTime promotionEndDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prm_typ_id", updatable = false, insertable = false)
	private PromotionType promotionType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prm_typ_id")
	private PromotionMechanic promotionMechanic;

	public Long getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Long promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getPromotionShortDesc() {
		return promotionShortDesc;
	}

	public void setPromotionShortDesc(String promotionShortDesc) {
		this.promotionShortDesc = promotionShortDesc;
	}

	public String getPromotionLongDesc() {
		return promotionLongDesc;
	}

	public void setPromotionLongDesc(String promotionLongDesc) {
		this.promotionLongDesc = promotionLongDesc;
	}

	public LocalDateTime getPromotionStartDate() {
		return promotionStartDate;
	}

	public void setPromotionStartDate(LocalDateTime promotionStartDate) {
		this.promotionStartDate = promotionStartDate;
	}

	public LocalDateTime getPromotionEndDate() {
		return promotionEndDate;
	}

	public void setPromotionEndDate(LocalDateTime promotionEndDate) {
		this.promotionEndDate = promotionEndDate;
	}

	public PromotionType getPromotionType() {
		return promotionType;
	}

	public void setPromotionType(PromotionType promotionType) {
		this.promotionType = promotionType;
	}

	public PromotionMechanic getPromotionMechanic() {
		return promotionMechanic;
	}

	public void setPromotionMechanic(PromotionMechanic promotionMechanic) {
		this.promotionMechanic = promotionMechanic;
	}

}
