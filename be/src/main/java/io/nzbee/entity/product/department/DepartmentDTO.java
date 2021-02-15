package io.nzbee.entity.product.department;

import java.io.Serializable;
import java.util.Map;

public class DepartmentDTO implements Serializable {
	
	private static final long serialVersionUID = -3285099992446985025L;

	public static final String ID_ALIAS = "dept_id";
	
	public static final String CODE_ALIAS = "dept_cd";
    
    public static final String DESC_ALIAS = "dept_desc";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
	
	private Long departmentId;
	
	private String departmentCode;
	
	private String departmentDesc;
	
	private String locale;

	public DepartmentDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.departmentId 		= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.departmentCode 	= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.departmentDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
	}
	
	public DepartmentDTO(Long departmentId, String departmentCode, String departmentDesc, String locale) {
		this.departmentId 	= departmentId;
		this.departmentCode = departmentCode;
		this.departmentDesc = departmentDesc;
		this.locale			= locale;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public String getDepartmentDesc() {
		return departmentDesc;
	}

	public String getLocale() {
		return locale;
	}

}
