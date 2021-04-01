package io.nzbee.entity.promotion.mechanic;

import java.io.Serializable;
import java.util.Map;

public class PromotionMechanicDTO implements Serializable {

	private static final long serialVersionUID = -2920277244483237920L;

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

	public PromotionMechanicDTO(Long mechanicId, String mechanicCode, String mechanicDesc, String locale) {
		super();
		this.mechanicId = mechanicId;
		this.mechanicCode = mechanicCode;
		this.mechanicDesc = mechanicDesc;
		this.locale = locale;
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
