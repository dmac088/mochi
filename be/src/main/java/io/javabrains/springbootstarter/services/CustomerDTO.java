package io.javabrains.springbootstarter.services;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

//this is the grand daddy DTO
/*---------------------------testing DTO JSON---------------------------
{
	"userName":"dmac654331",
	"password":"magic7653",
	"enabled":true,
	"firstName":"Ronald",
	"lastName":"McDonald"
}
*/
//
//@JsonRootName(value = "customer")
@JsonTypeName(value = "customer")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class CustomerDTO {
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
    private String CustomerID;
    
    //@NotNull
    //@Size(min = 1)
    private String matchingPassword;
    
    private boolean enabled;
    
    
    public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @JsonIgnore
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
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [firstName=").append(givenName).append(", lastName=").append(familyName).append(", password=").append(password).append(", matchingPassword=").append(matchingPassword).append(", username=").append(userName)
                .append(", role=").append("]");
        return builder.toString();
    }

}