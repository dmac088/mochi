package io.nzbee.entity;


import java.util.ArrayList;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.nzbee.security.User;

@Entity
@Table(name = "party", schema = "mochi")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.MINIMAL_CLASS,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "@class")
public abstract class Party {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pty_id")
	private Long partyId;
	
	@ManyToOne
	@JoinColumn(name="pty_typ_id", nullable=false, updatable = false, insertable = true)
	private PartyType partyType;

	@OneToMany(mappedBy="roleParty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Role> partyRoles = new ArrayList<Role>();
	
	@OneToOne(mappedBy="userParty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
    private User partyUser;

	public Party() {
		
	}
	
	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
	public User getPartyUser() {
		return partyUser;
	}

	public void setPartyUser(User partyUser) {
		this.partyUser = partyUser;
	}

	public Party(Long id) {
		
	}
	
	public PartyType getPartyType() {
		return partyType;
	}

	public void setPartyType(PartyType partyType) {
		this.partyType = partyType;
	}
	
	public List<Role> getPartyRoles() {
		return this.partyRoles;
	}

	public void setPartyRoles(List<Role> partyRole) {
		this.partyRoles = partyRole;
	}
	
	public void addRole(Role role) {
		this.partyRoles.add(role);
	}
	
	public Role getPartyRole(Long roleId) {
		Role retRole = null;
		for(Role r : partyRoles) {
			if (r.getRoleId().equals(roleId)) {
				retRole = r;
			}
		}
		return retRole;
	}
	
	public Role getPartyRole(String roleTypeDesc) {
		Role retRole = null;
		for(Role r : partyRoles) {
			if (r.getRoleType().getRoleTypeDesc().equals(roleTypeDesc)) {
				retRole = r;
			}
		}
		return retRole;
	}
	
	public void addUser(User user) {
		this.partyUser = user;
	}
} 
