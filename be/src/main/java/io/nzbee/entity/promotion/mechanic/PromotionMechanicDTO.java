package io.nzbee.entity.promotion.mechanic;

import java.util.Map;

public class PromotionMechanicDTO {

	public static final String ID_ALIAS = "prm_mec_id";
	
	public static final String CODE_ALIAS = "prm_mec_cd";
	
	public static final String DESC_ALIAS = "prm_mec_desc";
	
	public static final String LOCALE_CODE_ALIAS = "lcl_cd";
	
	private Long mechanicId;
	
	private String mechanicCode;
	
	private String mechanicDesc;
	
	private String locale;
	
	public PromotionMechanicDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.mechanicId 		= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.mechanicCode 		= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.mechanicDesc 		= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.locale 			= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
	}

	public Long getMechanicId() {
		return mechanicId;
	}

	public String getMechanicCode() {
		return mechanicCode;
	}

	public String getMechanicDesc() {
		return mechanicDesc;
	}

	public String getLocale() {
		return locale;
	}

}
