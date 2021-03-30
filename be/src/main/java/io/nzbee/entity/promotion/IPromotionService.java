package io.nzbee.entity.promotion;

import java.util.Optional;
import io.nzbee.entity.ILocalizedService;

public interface IPromotionService extends ILocalizedService<PromotionDTO, PromotionEntity> {

	Optional<PromotionEntity> findByDesc(String promotionMechanicDesc);

	Optional<PromotionDTO> findByCouponCode(String locale, String couponCode);
	
}
