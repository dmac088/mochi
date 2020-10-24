package io.nzbee.entity.promotion.type;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface IPromotionTypeRepository extends CrudRepository<PromotionType, Long> {
		
	Optional<PromotionType> findByPromotionTypeCode(String promotionTypeCode);
	
	Optional<PromotionType> findByPromotionTypeDesc(String promotionTypeDesc);
	
	Set<PromotionType> findAll();
}

