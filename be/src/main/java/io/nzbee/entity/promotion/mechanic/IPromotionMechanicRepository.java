package io.nzbee.entity.promotion.mechanic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IPromotionMechanicRepository extends CrudRepository<PromotionMechanic, Long> {
		
	Optional<PromotionMechanic> findByPromotionMechanicCode(String promotionMechanicCode);
	
	Optional<PromotionMechanic> findByPromotionMechanicDesc(String promotionMechanicDesc);
	
	List<PromotionMechanic> findAll();
}

