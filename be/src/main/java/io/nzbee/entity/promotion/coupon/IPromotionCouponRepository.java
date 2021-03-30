package io.nzbee.entity.promotion.coupon;


import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import io.nzbee.entity.promotion.PromotionDTO;

public interface IPromotionCouponRepository extends CrudRepository<PromotionCouponEntity, Long> {

	Optional<PromotionDTO> findByPromotionCouponCode(String couponCode);
	
}

