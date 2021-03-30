package io.nzbee.entity.promotion.coupon;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import io.nzbee.entity.promotion.PromotionDTO;

public interface IPromotionCouponRepository extends CrudRepository<PromotionCouponEntity, Long> {

	@Query(
			"SELECT new io.nzbee.entity.promotion.coupon.PromotionCouponDTO (" +
			" pce.promotionId," +
			" pce.promotionCode," +
			" attr.promotionDesc," +
			" pce.promotionStartDate," +
			" pce.promotionEndDate," +
			" attr.locale, " +
			" pce.promotionCouponCode" +
			") " +
			"FROM PromotionCouponEntity pce " +
			"JOIN pce.attributes attr " + 
			"WHERE pce.promotionCouponCode = :couponCode " + 
			"AND attr.locale = :locale "		
	)
	Optional<PromotionDTO> findByPromotionCouponCode(String locale, String couponCode);
	
}

