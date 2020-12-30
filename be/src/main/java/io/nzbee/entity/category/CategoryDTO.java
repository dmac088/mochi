package io.nzbee.entity.category;

import java.util.Map;

import io.nzbee.search.ISearchDimension;

public abstract class CategoryDTO implements ISearchDimension {

	public static final String ID_ALIAS = "cat_id";
	
	public static final String CODE_ALIAS = "cat_cd";
    
    public static final String DESC_ALIAS = "cat_desc";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
    
    public static final String OBJECT_COUNT_ALIAS = "object_count";
    
	public static final String CATEGORY_TYPE_ALIAS = "cat_typ_cd";
	
	private Long categoryId;
	
	private String categoryCode;
	
	private String categoryDesc;
	
	private String locale;
	
	private Long objectCount;
	
	public CategoryDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.categoryId 	= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.categoryCode 	= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.categoryDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.locale 		= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
		this.objectCount	= new Long(0);
		if(!(tuple[aliasToIndexMap.get(OBJECT_COUNT_ALIAS)] == null)) {
			this.objectCount	= ((Number) tuple[aliasToIndexMap.get(OBJECT_COUNT_ALIAS)]).longValue();
		}
		
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
