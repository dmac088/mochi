package io.nzbee.entity.promotion.mechanic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IPromotionMechanicRepository extends CrudRepository<PromotionMechanicEntity, Long> {
		
	Optional<PromotionMechanicEntity> findByPromotionMechanicCode(String promotionMechanicCode);
	
	Optional<PromotionMechanicEntity> findByPromotionMechanicDesc(String promotionMechanicDesc);
	
	List<PromotionMechanicEntity> findAll();
}

