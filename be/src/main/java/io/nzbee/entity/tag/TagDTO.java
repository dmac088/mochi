package io.nzbee.entity.tag;

import java.util.Map;

public class TagDTO {

	public static final String ID_ALIAS = "tag_id";
	
	public static final String CODE_ALIAS = "tag_cd";
    
    public static final String DESC_ALIAS = "tag_desc";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
	
	private Long tagId;
	
	private String tagCode;
	
	private String tagDesc;
	
	private String locale;

	public TagDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.tagId 	= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.tagCode 	= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.tagDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.locale 	= tuple[aliasToIndexMap.get(LOCALE_CODE_ALIAS)].toString();
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
	
	
}
