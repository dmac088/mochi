package io.nzbee.entity.brand;

import java.io.Serializable;
import java.util.Map;

import io.nzbee.search.ISearchDimension;

public class BrandDTO implements ISearchDimension, Serializable {

	private static final long serialVersionUID = 7130627751498901160L;

	public static final String ID_ALIAS = "bnd_id";
	
	public static final String CODE_ALIAS = "bnd_cd";
    
    public static final String DESC_ALIAS = "bnd_desc";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
    
    public static final String COUNT_ALIAS = "object_count";
	
	private Long brandId;
	
	private String brandCode;
	
	private String brandDesc;
	
	private String locale;
	
	private Long count; 
	
	public BrandDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.brandId 	= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.brandCode 	= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.brandDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.locale 	= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
		this.count 		= !(aliasToIndexMap.get(COUNT_ALIAS) == null)
						  ? ((Number) tuple[aliasToIndexMap.get(COUNT_ALIAS)]).longValue()
						  : new Long(0);
	}

	public BrandDTO(Long brandId, String brandCode, String brandDesc, String locale) {
		super();
		this.brandId = brandId;
		this.brandCode = brandCode;
		this.brandDesc = brandDesc;
		this.locale = locale;
	}



	public static String getIdAlias() {
		return ID_ALIAS;
	}

	public static String getCodeAlias() {
		return CODE_ALIAS;
	}

	public static String getDescAlias() {
		return DESC_ALIAS;
	}

	public static String getLocaleCodeAlias() {
		return LOCALE_CODE_ALIAS;
	}

	public Long getBrandId() {
		return brandId;
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

	public Long getCount() {
		return count;
	}

	@Override
	public String getCode() {
		return this.brandCode;
	}

	@Override
	public String getDesc() {
		return this.brandDesc;
	}

	@Override
	public boolean isHierarchical() {
		return false;
	}
	
}
