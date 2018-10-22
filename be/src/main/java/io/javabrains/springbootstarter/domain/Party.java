package io.javabrains.springbootstarter.domain;


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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "party", schema = "mochi")
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
	@JoinColumn(name="pty_typ_id", nullable=false, updatable = false, insertable = true)
	private PartyType partyType;

	@OneToMany(mappedBy="roleParty", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Role> partyRole;
	
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JsonIgnore
    @JoinColumn(name = "pty_id", nullable = false)
    private Party partyUser;

	public Party() {
		
	}
	
	public Long getPartyId() {
		return PartyId;
	}

	public void setPartyId(Long partyId) {
		PartyId = partyId;
	}
	
	public Party(Long id) {
		
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
	
	public Party getPartyUser() {
		return partyUser;
	}

	public void setPartyUser(Party partyUser) {
		this.partyUser = partyUser;
	}
	
}
