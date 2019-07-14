package io.nzbee.entity.role;

import java.util.Date;

import javax.persistence.CascadeType;
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
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.nzbee.entity.party.Party;


@Entity
@Table(name = "role", schema = "mochi") 
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.MINIMAL_CLASS,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "@class")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	@Column(name="rle_id")
	private Long roleId;
	
	@Transient
	private Long roleTypeId = (long) 1;
	
	@Column(name="rle_start_dttm")
	private Date RoleStart; 
	
	@ManyToOne(fetch = FetchType.EAGER, optional=false)
	@JoinColumn(name="rle_typ_id", nullable=false)
	private RoleType roleType;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional=false)
	@JoinColumn(name="pty_id", nullable=false)
	private Party roleParty;


	public Role() {
		
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
	
	public String getClassName() {
		return this.getClass().toString();
	}
	
}
