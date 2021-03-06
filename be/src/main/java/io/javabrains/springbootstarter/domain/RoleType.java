package io.javabrains.springbootstarter.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_type", schema = "mochi")
public class RoleType {


	@Id
	@Column(name="rle_typ_id")
	private Long RoleTypeId;
	
	@Column(name="rle_typ_desc")
	private String roleTypeDesc;
	
	public RoleType() {
		// TODO Auto-generated constructor stub
	}

	public RoleType(long roleTypeId) {
		this.RoleTypeId = roleTypeId;
		// TODO Auto-generated constructor stub
	}

	public Long getRoleTypeId() {
		return this.RoleTypeId;
	}

	public void setRoleTypeId(Long roleTypeId) {
		this.RoleTypeId = roleTypeId;
	}

	public String getRoleTypeDesc() {
		return this.roleTypeDesc;
	}

	public void setRoleTypeDesc(String roleTypeDesc) {
		this.roleTypeDesc = roleTypeDesc;
	}
}
