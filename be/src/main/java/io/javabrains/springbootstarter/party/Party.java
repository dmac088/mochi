package io.javabrains.springbootstarter.party;



import java.util.ArrayList;
import java.util.List;

import io.javabrains.springbootstarter.role.Role;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "party")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Party {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pty_id")
	private Long PartyId;
	
	@Column(name="pty_usr_nm")
	private String UserName;
	
	@Column(name="pty_pwd")
	private String Password;
	
	@ManyToOne
	@JoinColumn(name="pty_typ_id", nullable=false)
	private PartyType partyType;

	@OneToMany(mappedBy="roleParty", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Role> partyRole;

	public Party() {
		this.partyRole = new ArrayList<Role>();
	}
	
	public Party(Long id) {
		this.PartyId = id;
		this.partyRole = new ArrayList<Role>();
	}
	
	public Long getPartyId() {
		return this.PartyId;
	}

	public void setPartyId(Long partyId) {
		this.PartyId = partyId;
	}
	
	public PartyType getPartyType() {
		return partyType;
	}

	public void setPartyType(PartyType partyType) {
		this.partyType = partyType;
	}
		
	public void setUserName(String userName) {
		UserName = userName;
	}
	
	public String getUserName() {
		return UserName;
	}

	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		Password = password;
	}
	
	public List<Role> getPartyRole() {
		return this.partyRole;
	}

	public void setPartyRole(List<Role> partyRole) {
		this.partyRole = partyRole;
	}
	
	public void addRole(Role role) {
		this.partyRole.add(role);
	}
}
