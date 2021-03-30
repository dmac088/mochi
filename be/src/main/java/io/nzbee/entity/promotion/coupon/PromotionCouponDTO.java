package io.nzbee.entity.promotion.coupon;

import java.time.LocalDateTime;
import java.util.Map;

import io.nzbee.entity.promotion.PromotionDTO;

public class PromotionCouponDTO extends PromotionDTO {

	private static final long serialVersionUID = -4596062542753000601L;

	public static final String COUPON_CODE_ALIAS = "prm_cpn_cd";
	
	private String couponCode;
	
	public PromotionCouponDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		super(tuple, aliasToIndexMap);
		
		this.couponCode = tuple[aliasToIndexMap.get(COUPON_CODE_ALIAS)].toString();
	}
	
	public PromotionCouponDTO(	Long   promotionId, 
								String promotionCode, 
								String promotionDesc, 
								LocalDateTime promotionStartDate,
								LocalDateTime promotionEndDate, 
								String locale,
								String couponCode) {
		super(promotionId,
			  promotionCode,
			  promotionDesc,
			  promotionStartDate,
			  promotionEndDate,
			  locale);
		
		this.couponCode = couponCode;
	}

	public String getCouponCode() {
		return couponCode;
	}

}
