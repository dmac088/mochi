package io.nzbee.entity.party.person;

import java.util.Map;

public class CustomerDTO {
	
	private Long personId;
	
	private String givenName;
	
	private String familyName;
	
	private String userName;
	
	private String customerNumber;
	
	private Boolean enabled;
	
	public CustomerDTO(Long personId,
					 String givenName,
					 String familyName,
					 String userName,
					 String customerNumber,
					 Boolean enabled
					 ) {
		this.personId 		= personId;
		this.givenName 		= givenName;
		this.familyName 	= familyName;
		this.userName		= userName;
		this.customerNumber	= customerNumber;
		this.enabled		= enabled;
	}

	public CustomerDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		// TODO Auto-generated constructor stub
	}

	public Long getPersonId() {
		return personId;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public String getUserName() {
		return userName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public Boolean getEnabled() {
		return enabled;
	}
	
}
