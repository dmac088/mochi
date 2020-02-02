package io.nzbee.entity.party.person;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import io.nzbee.entity.party.Party;

@Entity
@Table(name = "person", schema = "mochi")
@PrimaryKeyJoinColumn(name = "psn_id")
@DiscriminatorValue("1")
public class Person extends Party  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2300017359584028443L;

	@Column(name="psn_gvn_nm")
	private String givenName;
	
	@Column(name="psn_fml_nm")
	private String FamilyName;
	
	public String getGivenName() {
		return givenName; 
	}	
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return FamilyName;
	}

	public void setFamilyName(String familyName) {
		FamilyName = familyName;
	}

	
}
