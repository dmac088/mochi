package io.nzbee.entity.promotion.coupon;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import io.nzbee.entity.promotion.PromotionEntity;

@Entity
@Table(name = "promotion_coupon", schema = "mochi")
@DiscriminatorValue("2")
public class PromotionCouponEntity extends PromotionEntity {

	private static final long serialVersionUID = 9148075546941323177L;
	
	private String promotionCode;

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
	
}
