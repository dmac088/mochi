package io.nzbee.entity.promotion.order;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import io.nzbee.entity.promotion.PromotionEntity;

@Entity
@Table(name = "promotion_order", schema = "mochi")
@DiscriminatorValue("2")
public class PromotionOrderEntity extends PromotionEntity {

	private static final long serialVersionUID = 9148075546941323177L;
	
	@Column(name="prm_cpn_cd")
	private String promotionCouponCode;

	public String getPromotionCouponCode() {
		return promotionCouponCode;
	}

	public void setPromotionCouponCode(String promotionCouponCode) {
		this.promotionCouponCode = promotionCouponCode;
	}
	
}
