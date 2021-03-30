package io.nzbee.entity.promotion.coupon;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import io.nzbee.entity.promotion.PromotionDTO;

public interface IPromotionCouponRepository extends CrudRepository<PromotionCouponEntity, Long> {

	@Query(
			"SELECT new io.nzbee.entity.promotion.coupon.PromotionCouponDTO (" +
			"" +
			"" +
			"" +
			"FROM PromotionCouponEntity pce " +
			"WHERE pce.promotionCouponCode = :couponCode "
			
	)
	Optional<PromotionDTO> findByPromotionCouponCode(String couponCode);
	
}

