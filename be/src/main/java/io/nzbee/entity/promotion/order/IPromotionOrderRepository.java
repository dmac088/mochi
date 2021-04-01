package io.nzbee.entity.promotion.order;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IPromotionOrderRepository extends CrudRepository<PromotionOrderEntity, Long> {

	List<PromotionOrderEntity> findAll();
	
	Optional<PromotionOrderEntity> findByPromotionCode(String promotionCode);
	
	@Query(
			"SELECT new io.nzbee.entity.promotion.order.PromotionOrderDTO (" +
			" pce.promotionId," +
			" pce.promotionCode," +
			" attr.promotionDesc," +
			" pm.promotionMechanicId, " +
			" pm.promotionMechanicCode, " +
			" pm.promotionMechanicDesc, " +
			" pt.promotionTypeId, " +
			" pt.promotionTypeCode, " +
			" pt.promotionTypeDesc, " +
			" pce.promotionStartDate," +
			" pce.promotionEndDate," +
			" attr.locale, " +
			" pce.promotionCouponCode" +
			") " +
			"FROM PromotionOrderEntity pce " +
			"JOIN pce.attributes attr " + 
			"JOIN pce.promotionMechanic pm " +
			"JOIN pce.promotionType pt " +
			"WHERE pce.promotionCode = :promotionCode " + 
			"AND attr.locale = :locale "		
	)
	Optional<PromotionOrderDTO> findByCode(String locale, String promotionCode);
	
	@Query(
			"SELECT new io.nzbee.entity.promotion.order.PromotionOrderDTO (" +
			" pce.promotionId," +
			" pce.promotionCode," +
			" attr.promotionDesc," +
			" pm.promotionMechanicId, " +
			" pm.promotionMechanicCode, " +
			" pm.promotionMechanicDesc, " +
			" pt.promotionTypeId, " +
			" pt.promotionTypeCode, " +
			" pt.promotionTypeDesc, " +
			" pce.promotionStartDate," +
			" pce.promotionEndDate," +
			" attr.locale, " +
			" pce.promotionCouponCode" +
			") " +
			"FROM PromotionOrderEntity pce " +
			"JOIN pce.attributes attr " + 
			"JOIN pce.promotionMechanic pm " +
			"JOIN pce.promotionType pt " +
			"WHERE pce.promotionCouponCode = :couponCode " + 
			"AND attr.locale = :locale "		
	)
	Optional<PromotionOrderDTO> findByPromotionCouponCode(String locale, String couponCode);
	
}

