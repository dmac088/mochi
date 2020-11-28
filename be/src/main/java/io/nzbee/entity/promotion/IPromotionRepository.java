package io.nzbee.entity.promotion;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface IPromotionRepository extends CrudRepository<PromotionEntity, Long> {
		
	Optional<PromotionEntity> findByPromotionCode(String promotionCode);
	
	Optional<PromotionEntity> findByAttributesLocaleAndAttributesPromotionDesc(String locale, String promotionDesc);
	
	Set<PromotionEntity> findAll();
}

