package io.nzbee.entity.promotion.level;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IPromotionLevelService extends IService<PromotionLevelEntity> {

	Optional<PromotionLevelEntity> findByCode(String code);

	Optional<PromotionLevelEntity> findById(Long id);
	
}
