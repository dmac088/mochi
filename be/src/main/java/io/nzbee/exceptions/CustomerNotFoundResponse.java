package io.nzbee.exceptions;

public class CustomerNotFoundResponse {

	private String customerNotFound;

	public CustomerNotFoundResponse(String customerNotFound) {
		this.setCustomerNotFound(customerNotFound);
	}

	public String getCustomerNotFound() {
		return customerNotFound;
	}

	public void setCustomerNotFound(String customerNotFound) {
		this.customerNotFound = customerNotFound;
	}
	
}
