package io.nzbee.entity.category;

import java.util.Map;

public class CategoryDTO {

	public static final String ID_ALIAS = "cat_id";
	
	public static final String CODE_ALIAS = "cat_cd";
    
    public static final String DESC_ALIAS = "cat_desc";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
	
	private Long categoryId;
	
	private String categoryCode;
	
	private String categoryDesc;
	
	private String locale;
	
	public CategoryDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.categoryId 	= ((java.math.BigInteger) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.categoryCode 	= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.categoryDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.locale 		= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
	}


	public Long getCategoryId() {
		return categoryId;
	}


	public String getCategoryCode() {
		return categoryCode;
	}


	public String getCategoryDesc() {
		return categoryDesc;
	}


	public String getLocale() {
		return locale;
	}
	
}
