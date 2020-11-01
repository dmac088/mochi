package io.nzbee.entity.party.person;

import java.util.Map;

import io.nzbee.security.user.User;

public class PersonDTO {
	
	public static final String ID_ALIAS = "psn_id";
	
	public static final String CODE_ALIAS = "cat_cd";
    
    public static final String DESC_ALIAS = "cat_desc";
    
	private Long personId;
	
	private String givenName;
	
	private String familyName;
	
	private User userDTO;
	
	public PersonDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap) {
		this.personId 		= ((Number) tuple[aliasToIndexMap.get(ID_ALIAS)]).longValue();
		this.givenName 		= tuple[aliasToIndexMap.get(CODE_ALIAS)].toString();
		this.familyName 	= tuple[aliasToIndexMap.get(DESC_ALIAS)].toString();
		this.userDTO 		= new UserDTO(Object[] tuple, Map<String, Integer> aliasToIndexMap);
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
	
}
