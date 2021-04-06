package io.nzbee.entity.promotion;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface IPromotionRepository extends CrudRepository<PromotionEntity, Long> {
		
	Optional<PromotionEntity> findByPromotionCode(String promotionCode);
	
	Optional<PromotionEntity> findByAttributesLocaleAndAttributesPromotionDesc(String locale, String promotionDesc);
	
	Optional<PromotionEntity> findByAttributesPromotionDesc(String promotionDesc);
	
	List<PromotionEntity> findAll();
	
}

