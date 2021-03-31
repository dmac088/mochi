package io.nzbee.domain.promotion.order;

import io.nzbee.domain.ILocalizedService;

public interface IOrderPromotionService extends ILocalizedService<OrderPromotion>  {

	OrderPromotion findByCouponCode(String locale, String couponCode);

}
