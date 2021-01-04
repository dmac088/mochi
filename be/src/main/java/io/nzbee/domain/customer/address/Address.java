package io.nzbee.domain.customer.address;

import io.nzbee.domain.customer.Customer;

public class Address {

	private Customer customer;

	private String addressLine1;

	private String addressLine2;

	private String addressLine3;

	private String country;

	private String postCode;

	private String addressTypeCode;

	private String addressTypeDesc;

	public Address(Customer customer, String addressLine1, String addressLine2, String addressLine3, String country,
			String postCode, String addressTypeCode, String addressTypeDesc) {

		this.customer = customer;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.country = country;
		this.postCode = postCode;
		this.addressTypeCode = addressTypeCode;
		this.addressTypeDesc = addressTypeDesc;
	}

	public Address(Customer customer, String addressLine1, String addressLine2, String addressLine3, String country,
			String postCode, String addressTypeCode) {

		this.customer = customer;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.country = country;
		this.postCode = postCode;
		this.addressTypeCode = addressTypeCode;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getCountry() {
		return country;
	}

	public String getAddressTypeCode() {
		return addressTypeCode;
	}

	public String getAddressTypeDesc() {
		return addressTypeDesc;
	}

	public Customer getCustomer() {
		return customer;
	}

}
