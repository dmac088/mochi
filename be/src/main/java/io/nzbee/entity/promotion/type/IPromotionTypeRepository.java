package io.nzbee.entity.promotion.type;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IPromotionTypeRepository extends CrudRepository<PromotionTypeEntity, Long> {
		
	Optional<PromotionTypeEntity> findByPromotionTypeCode(String promotionTypeCode);
	
	List<PromotionTypeEntity> findAll();
}

