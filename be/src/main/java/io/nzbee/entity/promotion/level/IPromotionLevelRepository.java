package io.nzbee.entity.promotion.level;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IPromotionLevelRepository extends CrudRepository<PromotionLevelEntity, Long> {
		
	Optional<PromotionLevelEntity> findByPromotionLevelCode(String promotionTypeCode);
	
	List<PromotionLevelEntity> findAll();
}

