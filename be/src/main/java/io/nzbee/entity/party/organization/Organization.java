package io.nzbee.entity.party.organization;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import io.nzbee.entity.party.Party;

@Entity
@Table(name = "organisation", schema = "mochi")
@DiscriminatorValue("2")
public class Organization extends Party  {
	
	@Column(name="org_nme")
	private String organizationName;
	
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
}
