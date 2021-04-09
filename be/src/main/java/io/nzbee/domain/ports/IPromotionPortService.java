package io.nzbee.domain.ports;

import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.order.OrderPromotion;

public interface IPromotionPortService extends IDomainPortService<Promotion> {

	Promotion findByCode(String locale, String code);
	
	OrderPromotion findOrderPromotionByCode(String locale, String code);

	OrderPromotion findOrderPromotionByCouponCode(String locale, String couponCode);
	
}
