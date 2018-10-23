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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.javabrains.springbootstarter.security.User;

@Entity
@Table(name = "party", schema = "mochi")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Party {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pty_id")
	private Long PartyId;
	
	@ManyToOne
	@JoinColumn(name="pty_typ_id", nullable=false, updatable = false, insertable = true)
	private PartyType partyType;

	@OneToMany(mappedBy="roleParty", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Role> partyRole;
	
	 
	
	@OneToOne
	@JoinColumn(name = "pty_id")
    private User partyUser;

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
	
	public List<Role> getPartyRole() {
		return this.partyRole;
	}

	public void setPartyRole(List<Role> partyRole) {
		this.partyRole = partyRole;
	}
	
	public void addRole(Role role) {
		this.partyRole.add(role);
	}
	
	public User getPartyUser() {
		return partyUser;
	}

	public void setPartyUser(User partyUser) {
		this.partyUser = partyUser;
	}
	
} 
