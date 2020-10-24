package io.nzbee.entity.promotion.mechanic;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface IPromotionMechanicRepository extends CrudRepository<PromotionMechanic, Long> {
		
	Optional<PromotionMechanic> findByPromotionMechanicCode(String promotionMechanicCode);
	
	Optional<PromotionMechanic> findByPromotionMechanicDesc(String promotionMechanicDesc);
	
	Set<PromotionMechanic> findAll();
}

