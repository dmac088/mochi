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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Table(name = "role")
@Inheritance(strategy = InheritanceType.JOINED)
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	@Column(name="rle_id")
	private Long roleId;
	
	@Column(name="rle_start_dttm")
	private Date RoleStart; 
	
	@ManyToOne
	@JoinColumn(name="rle_typ_id", nullable=false)
	private RoleType roleType;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@JoinColumn(name="pty_id")
	private Party roleParty;
	

	public Role() {
		this.RoleStart = new Date();
	}
	
	public Role(String roleTypeDesc) {
		this.RoleStart = new Date();
	}

	public Role(Long id, String roleTypeDesc) {
		this.roleId = id;
		this.RoleStart = new Date();
	}
	
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
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
	
}
