package io.nzbee.entity.promotion;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IPromotionService extends IService<Promotion> {

	Optional<Promotion> findByDesc(String promotionMechanicDesc);
	
}
