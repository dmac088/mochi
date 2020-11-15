package io.nzbee.entity.party.person;

import java.math.BigInteger;
import java.util.Map;

public class CustomerDTO {
	
	public static final String ID_ALIAS = "pty_id";
	
	public static final String GIVEN_NAME_ALIAS = "psn_gvn_nm";
	
	public static final String FAMILY_NAME_ALIAS = "psn_fml_nm";
	
	public static final String USERNAME_NAME_ALIAS = "user_name";
	
	public static final String CUSTOMER_NUMBER_ALIAS = "cst_num";
	
	public static final String ENABLED_ALIAS = "enabled";
	
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
		this.personId 			= ((BigInteger) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.givenName 			= tuple[aliasToIndexMap.get(GIVEN_NAME_ALIAS)].toString();
		this.familyName 		= tuple[aliasToIndexMap.get(FAMILY_NAME_ALIAS)].toString();
		this.userName			= tuple[aliasToIndexMap.get(FAMILY_NAME_ALIAS)].toString();
		this.customerNumber		= tuple[aliasToIndexMap.get(CUSTOMER_NUMBER_ALIAS)].toString();
		this.enabled			= (Boolean) tuple[aliasToIndexMap.get(ENABLED_ALIAS)];
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
