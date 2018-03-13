package io.javabrains.springbootstarter.role;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_type")
public class RoleType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rle_typ_id")
	private Long roleTypeID;
	
	@Column(name="rle_typ_desc")
	private String roleTypeDesc;
	
	public RoleType() {
		
	}
	
	public RoleType(String roleTypeDesc) {
		this.roleTypeDesc = roleTypeDesc;
	}
	
	
	public Long getRoleTypeID() {
		return roleTypeID;
	}
	
	public void setRoleTypeID(Long RoleTypeID) {
		this.roleTypeID = RoleTypeID;
	}
	
	public String getRoleTypeDesc() {
		return roleTypeDesc;
	}
	
	public void setRoleTypeDesc(String roleTypeDesc) {
		this.roleTypeDesc = roleTypeDesc;
	}
	
}
