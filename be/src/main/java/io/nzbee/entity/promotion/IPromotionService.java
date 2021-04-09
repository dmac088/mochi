package io.nzbee.entity.promotion;

import java.util.Optional;
import io.nzbee.entity.IService;

public interface IPromotionService extends IService<PromotionEntity> {

	Optional<PromotionEntity> findByDesc(String promotionDesc);

	Optional<PromotionEntity> findByCode(String promotionCode);

	Optional<PromotionEntity> findById(Long promotionId);
	
}
