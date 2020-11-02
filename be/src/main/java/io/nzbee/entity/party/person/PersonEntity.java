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
public class PersonEntity extends Party  implements Serializable {
	
	private static final long serialVersionUID = -5851002761066421365L;

	@Column(name="psn_gvn_nm")
	private String givenName;
	
	@Column(name="psn_fml_nm")
	private String familyName;
	
	@Column(name="enb")
	private boolean enabled;

	public String getGivenName() {
		return givenName; 
	}	
	
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
