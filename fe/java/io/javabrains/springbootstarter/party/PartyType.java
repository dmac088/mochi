package io.javabrains.springbootstarter.party;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "party_type")
public class PartyType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pty_typ_id")
	private Long partyTypeID;
	
	@Column(name="pty_typ_desc")
	private String partyTypeDesc;
	
	public PartyType() {
		
	}
	
	public Long getPartyTypeID() {
		return partyTypeID;
	}
	
	public void setPartyTypeID(Long partyTypeID) {
		this.partyTypeID = partyTypeID;
	}
	
	public PartyType(String PartyTypeDesc) {
		this.partyTypeDesc = PartyTypeDesc;
	}
	
	public String getPartyTypeDesc() {
		return partyTypeDesc;
	}
	
	public void setPartyTypeDesc(String partyTypeDesc) {
		this.partyTypeDesc = partyTypeDesc;
	}
}
