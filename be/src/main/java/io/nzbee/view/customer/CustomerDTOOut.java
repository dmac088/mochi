package io.nzbee.view.customer;

public class CustomerDTOOut {

    private String customerId;
	
    private String givenName;
    
    private String familyName;

	public CustomerDTOOut() {
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

}
