package io.nzbee.dto.customer;

public class CustomerDTOOut {

    private String customerId;
	
    private String givenName;
    
    private String fammilyName;

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

	public String getFammilyName() {
		return fammilyName;
	}

	public void setFammilyName(String fammilyName) {
		this.fammilyName = fammilyName;
	}

}
