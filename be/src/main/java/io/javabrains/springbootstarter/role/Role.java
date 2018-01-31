package io.javabrains.springbootstarter.role;

import io.javabrains.springbootstarter.party.Party;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "role")
@Inheritance(strategy = InheritanceType.JOINED)
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	@Column(name="role_id")
	private Long roleId;
	
	@Column(name="role_start_dttm")
	private Date RoleStart; 

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="role_typ_id")
	private RoleType roleType;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="party_id")
	private Party roleParty;
	

	public Role() {
		this.RoleStart = new Date();
	}
	
	public Role(String roleTypeDesc) {
		this.roleType = new RoleType(roleTypeDesc);
		this.RoleStart = new Date();
	}
	
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Role(Long id, String roleTypeDesc) {
		this.roleId = id;
		this.roleType = new RoleType(roleTypeDesc);
		this.RoleStart = new Date();
	}
	
	public Party getRoleParty() {
		return roleParty;
	}

	public void setRoleParty(Party roleParty) {
		this.roleParty = roleParty;
	}

	public Date getRoleStart() {
		return RoleStart;
	}

	public void setRoleStart(Date roleStart) {
		RoleStart = roleStart;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
	
}
