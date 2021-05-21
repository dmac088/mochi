package io.nzbee.entity.tag.view.facet;

import java.io.Serializable;
import java.util.Map;
import io.nzbee.search.ISearchDimension;

public class TagFacetDTO implements ISearchDimension, Serializable  {

	private static final long serialVersionUID = -5385810041656869889L;

	public static final String ID_ALIAS = "tag_id";
	
	public static final String CODE_ALIAS = "tag_cd";
    
    public static final String DESC_ALIAS = "tag_desc";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
    
    public static final String OBJECT_COUNT_ALIAS = "object_count";
	
	private Long tagId;
	
	private String tagCode;
	
	private String tagDesc;
	
	private String locale;
	
	private Long objectCount;

	public TagFacetDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.tagId 			= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.tagCode 		= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.tagDesc 		= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.locale 		= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
		this.objectCount 	= !(aliasToIndexMap.get(OBJECT_COUNT_ALIAS) == null)
							  ? ((Number) tuple[aliasToIndexMap.get(OBJECT_COUNT_ALIAS)]).longValue()
							  : Long.valueOf(0);
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

	public Long getTagId() {
		return tagId;
	}

	public String getTagCode() {
		return tagCode;
	}

	public String getTagDesc() {
		return tagDesc;
	}

	public String getLocale() {
		return locale;
	}

	@Override
	public String getCode() {
		return tagCode;
	}

	@Override
	public String getDesc() {
		return tagDesc;
	}
	
	@Override
	public Long getCount() {
		return this.objectCount;
	}

	@Override
	public boolean isHierarchical() {
		return false;
	}
	
	
}
