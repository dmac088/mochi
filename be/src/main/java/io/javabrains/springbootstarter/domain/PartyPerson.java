package io.javabrains.springbootstarter.domain;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "person", schema = "mochi")
@PrimaryKeyJoinColumn(name = "psn_id")
public class PartyPerson extends Party implements Serializable {

	@Transient
	private Long partyTypeId = (long) 1;
	
	@Column(name="psn_gvn_nm_en")
	private String GivenNameEn;
	
	@Column(name="psn_fml_nm_en")
	private String FamilyNameEn;
	
	@Column(name="psn_nm_cn")
	private String NameCn;
	
	public PartyPerson() {
		super();
		this.setPartyType(new PartyType(partyTypeId));
	}
	
	public String getGivenNameEn() {
		return GivenNameEn; 
	}	
	
	public void setGivenNameEn(String givenNameEn) {
		GivenNameEn = givenNameEn;
	}

	public String getFamilyNameEn() {
		return FamilyNameEn;
	}

	public void setFamilyNameEn(String familyNameEn) {
		FamilyNameEn = familyNameEn;
	}

	public String getNameCn() {
		return NameCn;
	}

	public void setNameCn(String NameCn) {
		this.NameCn = NameCn;
	}
	
}
