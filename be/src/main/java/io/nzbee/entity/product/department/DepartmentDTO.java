package io.nzbee.entity.product.department;

import java.util.Map;

public class DepartmentDTO {
	
	public static final String ID_ALIAS = "dept_id";
	
	public static final String CODE_ALIAS = "dept_cd";
    
    public static final String DESC_ALIAS = "dept_desc";
    
    public static final String LOCALE_CODE_ALIAS = "lcl_cd";
	
	private Long departmentId;
	
	private String departmentCode;
	
	private String departmentDesc;
	
	private String locale;

	public DepartmentDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.departmentId 	= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.departmentCode 	= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.departmentDesc 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentDesc() {
		return departmentDesc;
	}

	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}

	public String getLocale() {
		return locale;
	}

}