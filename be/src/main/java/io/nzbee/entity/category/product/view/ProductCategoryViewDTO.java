package io.nzbee.entity.category.product.view;

import java.io.Serializable;
import java.util.Map;

import io.nzbee.search.ISearchDimension;

public class ProductCategoryViewDTO implements ISearchDimension, Serializable {
	
	private static final long serialVersionUID = -4167714174959634389L;

	public static final String ID_ALIAS = "cat_id";
	
	public static final String CODE_ALIAS = "cat_cd";
    
    public static final String DESC_ALIAS = "cat_desc";
    
    public static final String PARENT_CODE_ALIAS = "cat_prnt_cd";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
    
    public static final String CHILD_COUNT_ALIAS = "child_cat_count";
    
    public static final String OBJECT_COUNT_ALIAS = "object_count";
    
	public static final String CATEGORY_TYPE_ALIAS = "cat_typ_cd";
	
	private Long categoryId;
	
	private String categoryCode;
	
	private String categoryDesc;
	
	private String parentCode;
	
	private String locale;
	
	private Long childCount;
	
	private Long objectCount;
	
	public ProductCategoryViewDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.categoryId 	= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.categoryCode 	= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.categoryDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		if(!(tuple[aliasToIndexMap.get(PARENT_CODE_ALIAS)] == null)) {
			this.parentCode		= tuple[aliasToIndexMap.get(PARENT_CODE_ALIAS)].toString();
		}
		this.locale 		= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
		this.childCount		= ((Number) tuple[aliasToIndexMap.get(CHILD_COUNT_ALIAS)]).longValue();
		this.objectCount	= ((Number) tuple[aliasToIndexMap.get(OBJECT_COUNT_ALIAS)]).longValue();	
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


	@Override
	public String getCode() {
		return this.categoryCode;
	}


	@Override
	public String getDesc() {
		return this.categoryDesc;
	}

	
	public String getParentCode() {
		return parentCode;
	}
	
	public Long getChildCount() {
		return childCount;
	}

	public void setChildCount(Long childCount) {
		this.childCount = childCount;
	}

	@Override
	public Long getCount() {
		return this.objectCount;
	}


	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return false;
	}
}