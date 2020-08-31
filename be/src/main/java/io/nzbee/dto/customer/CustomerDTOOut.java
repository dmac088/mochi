package io.nzbee.dto.customer;

public class CustomerDTOOut {

    private String customerId;
	
    private String givenName;

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

}
