package io.javabrains.springbootstarter.organization;

import io.javabrains.springbootstarter.party.Party;

public class Organization extends Party {
	private String OrganizationName;

	public String getOrganizationName() {
		return OrganizationName;
	}

	public void setOrganizationName(String OrganizationName) {
		this.OrganizationName = OrganizationName;
	}
	
	
}
