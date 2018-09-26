package io.javabrains.springbootstarter.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_type")
public class RoleType {


	@Id
	@Column(name="rle_typ_id")
	private Long RoleTypeId;
	
	@Column(name="rle_typ_desc")
	private String roleTypeDesc;
	
	public Long getRoleTypeId() {
		return RoleTypeId;
	}

	public void setRoleTypeId(Long roleTypeId) {
		RoleTypeId = roleTypeId;
	}

	public String getRoleTypeDesc() {
		return roleTypeDesc;
	}

	public void setRoleTypeDesc(String roleTypeDesc) {
		this.roleTypeDesc = roleTypeDesc;
	}
}
