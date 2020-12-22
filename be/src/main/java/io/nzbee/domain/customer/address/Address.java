package io.nzbee.domain.customer.address;

public class Address {

	private String addressLine1;
	
	private String addressLine2;
	
	private String addressLine3;
	
	private String postCode;
	
	private String addressTypeCode;
	
	private String addressTypeDesc;
	
	public Address(
				String addressLine1,
				String addressLine2,
				String addressLine3,
				String country,
				String postCode,
				String addressTypeCode, 
				String addressTypeDesc
			) {
		
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

	public String getAddressTypeCode() {
		return addressTypeCode;
	}

	public String getAddressTypeDesc() {
		return addressTypeDesc;
	}
	
}
