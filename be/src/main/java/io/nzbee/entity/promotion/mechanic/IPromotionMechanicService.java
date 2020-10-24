package io.nzbee.entity.promotion.mechanic;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IPromotionMechanicService extends IService<PromotionMechanic> {

	Optional<PromotionMechanic> findByDesc(String promotionMechanicDesc);
	
}
