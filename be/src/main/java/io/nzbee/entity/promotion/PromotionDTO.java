package io.nzbee.entity.promotion;

import java.util.Map;

public class PromotionDTO {

	public static final String ID_ALIAS = "prm_id";
	
	public static final String CODE_ALIAS = "prm_cd";
	
	public static final String DESC_ALIAS = "prm_desc";
	
	public static final String LOCALE_CODE_ALIAS = "lcl_cd";
	
	private Long promotionId;
	
	private String promotionCode;
	
	private String promotionDesc;
	
	private String locale;
	
	
	public PromotionDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.promotionId 	= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.promotionCode 	= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.promotionDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.locale 		= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
	}

	public Long getPromotionId() {
		return promotionId;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public String getPromotionDesc() {
		return promotionDesc;
	}

	public String getLocale() {
		return locale;
	}
	
}
