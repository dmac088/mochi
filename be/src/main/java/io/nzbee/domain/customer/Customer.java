package io.nzbee.domain.customer;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import io.nzbee.domain.IDomainObject;

public class Customer implements IDomainObject {
    @NotNull
    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String givenName;

	@NotNull
    @Size(min = 1, message = "{Size.userDto.lastName}")
    private String familyName;

	//@ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String userName;
    
    //@ValidPassword
    private String password;
    
	@NotNull
    private String customerId;
	
	private String partyType;

	//@NotNull
    //@Size(min = 1)
    private String matchingPassword;
    
    private boolean enabled;
    
    
    public String getCustomerID() {
		return customerId;
	}

	public void setCustomerID(String customerId) {
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

    public void setUserName(final String username) {
        this.userName = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(final String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
    
    public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String getPartyType() {
		return partyType;
	}

	public void setPartyType(String partyType) {
		this.partyType = partyType;
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
        				.append(", password=").append(password)
        				.append(", matchingPassword=").append(matchingPassword)
        				.append(", username=").append(userName)
        				.append(", role=").append("]");
        return builder.toString();
    }
 
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.getCustomerID();
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrency() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return false;
	}

}