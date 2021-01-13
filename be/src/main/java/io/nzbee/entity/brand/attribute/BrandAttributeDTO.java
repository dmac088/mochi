package io.nzbee.entity.brand.attribute;

import java.io.Serializable;
import java.util.Map;

public class BrandAttributeDTO implements Serializable {

	private static final long serialVersionUID = -4554924370162082157L;

	public static final String ID_ALIAS = "bnd_id";
	
	public static final String BRAND_ID_ALIAS = "bnd_ID";
    
    public static final String DESC_ALIAS = "bnd_desc";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
	
	private Long brandAttributeId;
	
	private Long brandId;
	
	private String brandAttributeDesc;
	
	private String locale;

	
	public BrandAttributeDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.brandAttributeId 		= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.brandId 				= ((Number) tuple[aliasToIndexMap.get(BRAND_ID_ALIAS)]).longValue();
		this.brandAttributeDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.locale					= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
	}


	public static String getIdAlias() {
		return ID_ALIAS;
	}


	public static String getBrandIdAlias() {
		return BRAND_ID_ALIAS;
	}


	public static String getDescAlias() {
		return DESC_ALIAS;
	}


	public static String getLocaleCodeAlias() {
		return LOCALE_CODE_ALIAS;
	}


	public Long getBrandAttributeId() {
		return brandAttributeId;
	}


	public Long getBrandId() {
		return brandId;
	}


	public String getBrandAttributeDesc() {
		return brandAttributeDesc;
	}


	public String getLocale() {
		return locale;
	}
	
	
	
}
