package io.nzbee.view.customer;

public class CustomerDTOIn {

    private String customerId;
	
    private String givenName;

    private String familyName;

    private String userName;
    
    private String password;
    
    private String confirmPassword;
    
	private String partyType;
    
    private boolean enabled;
	
	public CustomerDTOIn() {
	}
    
	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


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


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getConfirmPassword() {
		return confirmPassword;
	}

	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	public String getPartyType() {
		return partyType;
	}


	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
