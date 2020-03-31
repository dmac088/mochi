package io.nzbee.entity.product.department;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department", schema = "mochi")
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="dept_id")
	private Long departmentId;

	@Column(name="dept_cd")
	private String departmentCode;
	
	@Column(name="dept_class")
	private String departmentClass;

	public Long getId() {
		return departmentId;
	}

	public void setId(Long id) {
		this.departmentId = id;
	}
	
	public String getCode() {
		return departmentCode;
	}

	public void setCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentClass() {
		return departmentClass;
	}

	public void setDepartmentClass(String departmentClass) {
		this.departmentClass = departmentClass;
	}
	
}
