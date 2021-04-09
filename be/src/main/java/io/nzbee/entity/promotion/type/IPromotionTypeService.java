package io.nzbee.entity.promotion.type;

import java.util.Optional;

import io.nzbee.entity.IService;

public interface IPromotionTypeService extends IService<PromotionTypeEntity> {

	Optional<PromotionTypeEntity> findByCode(String code);

	Optional<PromotionTypeEntity> findById(Long id);
	
}
