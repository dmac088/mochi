package io.nzbee.entity.category.product;

import java.io.Serializable;
import java.util.Map;

public class CategoryProductParentDTO implements Serializable {

	private static final long serialVersionUID = 5421781378392242942L;

	public static final String ID_ALIAS = "cat_prnt_id";
	
	public static final String CODE_ALIAS = "cat_prnt_cd";
    
    public static final String DESC_ALIAS = "cat_prnt_desc";
     
    public static final String LEVEL_ALIAS = "cat_prnt_lvl";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
	
	private Long categoryId;
	
	private String categoryCode;
	
	private String categoryDesc;
	
	private Long categoryLevel;
	
	private String locale;
	
	
	
	public CategoryProductParentDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.categoryId 	= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.categoryCode 	= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		//this.categoryDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.locale 		= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
		this.categoryLevel 	= ((Number) tuple[aliasToIndexMap.get(LEVEL_ALIAS)]).longValue();
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

	public Long getCategoryLevel() {
		return categoryLevel;
	}
	
	
}
