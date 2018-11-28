package io.javabrains.springbootstarter.services;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class CustomerDTO {
    @NotNull
    @Size(min = 1, message = "{Size.userDto.firstName}")
    private String firstName;

    @NotNull
    @Size(min = 1, message = "{Size.userDto.lastName}")
    private String lastName;

    //@ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String userName;
    
    //@ValidPassword
    private String password;
    
    //@NotNull
    //@Size(min = 1)
    private String matchingPassword;
    
    private boolean enabled;

	public String getUserName() {
        return userName;
    }

    public void setUserName(final String username) {
        this.userName = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
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
    
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [firstName=").append(firstName).append(", lastName=").append(lastName).append(", password=").append(password).append(", matchingPassword=").append(matchingPassword).append(", username=").append(userName)
                .append(", role=").append("]");
        return builder.toString();
    }

}