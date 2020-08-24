package io.nzbee.domain.customer;

import java.util.Objects;
import io.nzbee.domain.bag.Bag;


//we don't bother about dealing with party, even though our data model and persistence layer cater
//for both persons and organizations in the "Role" of customers, we're only interested in running a 
//B2C business, therefore all customers will be Persons (at least through the web front end for now)
public class Customer {
    
	private Bag bag;

	//@NotNull
    private String customerId;
	
	//@NotNull
    //@Size(min = 1, message = "{Size.userDto.firstName}")
    private String givenName;

	//@NotNull
    //@Size(min = 1, message = "{Size.userDto.lastName}")
    private String familyName;

	//@ValidEmail
    //@NotNull
    //@Size(min = 1, message = "{Size.userDto.email}")
    private String userName;
    
    //@ValidPassword
    private String password;
    
	private String partyType;
    
    private boolean enabled;
    
    public Customer(String givenName,
    				String familyName,
    				String userName,
    				String customerId,
    				boolean isEnabled) {
    	
    	this.userName = userName;
    	this.customerId = customerId;
    	this.givenName = givenName;
    	this.familyName = familyName;
    	this.partyType = "Person";
    	this.enabled = isEnabled;
    	this.bag = new Bag(this);
    }

	public String getCustomerID() {
		return customerId;
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
    
    public boolean isEnabled() {
		return enabled;
	}
	
	public String getPartyType() {
		return partyType;
	}
	
	public void setPassword(String password, String matchingPassword) {
		if(password.equals(matchingPassword)) {
			this.password = password;
		}
	}
	
	public String getPassword() {
		return password;
	}
	
    public Bag getBag() {
		return bag;
	}
 	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Customer pcDto = (Customer) o;
	     return this.customerId == pcDto.customerId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId);
	}
	
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Customer [CustomerId=").append(customerId)
        				.append(", givenName=").append(givenName)
        				.append(", familyName=").append(familyName)
        				.append(", username=").append(userName)
        				.append(", role=").append("]");
        return builder.toString();
    }

}