package io.nzbee.entity.party.person;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.PartyType;

@Entity
@Table(name = "person", schema = "mochi")
@PrimaryKeyJoinColumn(name = "psn_id")
@DiscriminatorValue("1")
public class Person extends Party {

	@Transient
	private Long partyTypeId = (long) 1;
	
	@Column(name="psn_gvn_nm")
	private String GivenName;
	
	@Column(name="psn_fml_nm")
	private String FamilyName;
	
	public Person() {
		super();
		this.setPartyType(new PartyType(partyTypeId));
	}
	
	public String getGivenName() {
		return GivenName; 
	}	
	
	public void setGivenName(String givenName) {
		GivenName = givenName;
	}

	public String getFamilyName() {
		return FamilyName;
	}

	public void setFamilyName(String familyName) {
		FamilyName = familyName;
	}

	
}
