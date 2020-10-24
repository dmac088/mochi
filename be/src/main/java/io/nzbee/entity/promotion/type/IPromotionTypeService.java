package io.nzbee.entity.promotion.type;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IPromotionTypeService extends IService<PromotionType> {

	Optional<PromotionType> findByDesc(String promotionMechanicDesc);
	
}
