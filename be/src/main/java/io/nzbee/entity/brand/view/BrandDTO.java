package io.nzbee.entity.brand.view;

import java.io.Serializable;
import java.util.Map;

public class BrandDTO implements Serializable {
	
	private static final long serialVersionUID = 5082157529149474395L;

	public static final String CODE_ALIAS = "bnd_cd";
    
    public static final String DESC_ALIAS = "bnd_desc";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
	
	private String brandCode;
	
	private String brandDesc;
	
	private String locale;
	
	public BrandDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.brandCode 	= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.brandDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.locale 	= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
	}

	public BrandDTO(String brandCode, String brandDesc, String locale) {
		super();
		this.brandCode = brandCode;
		this.brandDesc = brandDesc;
		this.locale = locale;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public String getLocale() {
		return locale;
	}	
	
}
