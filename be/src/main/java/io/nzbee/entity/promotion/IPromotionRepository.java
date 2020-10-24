package io.nzbee.entity.promotion;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface IPromotionRepository extends CrudRepository<Promotion, Long> {
		
	Optional<Promotion> findByPromotionCode(String promotionMechanicCode);
	
	Optional<Promotion> findByPromotionShortDesc(String promotionMechanicDesc);
	
	Set<Promotion> findAll();
}

