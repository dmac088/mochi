package io.javabrains.springbootstarter.organization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import io.javabrains.springbootstarter.party.Party;

@Entity
@Table(name = "organisation", schema = "mochi")
@PrimaryKeyJoinColumn(name = "org_id")
public class Organization extends Party {
	
	@Column(name="org_nme")
	private String OrganizationName;

	public String getOrganizationName() {
		return OrganizationName;
	}

	public void setOrganizationName(String OrganizationName) {
		this.OrganizationName = OrganizationName;
	}
	
	
}
