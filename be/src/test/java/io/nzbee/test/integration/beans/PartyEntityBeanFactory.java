package io.nzbee.test.integration.beans;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.role.customer.CustomerEntity;
import io.nzbee.security.user.User;
import io.nzbee.security.user.role.IUserRoleService;
import io.nzbee.security.user.role.UserRole;

@Service(value = "partyEntityBeanFactory")
@Profile(value = "tst")
public class PartyEntityBeanFactory {

	@Autowired
	private IUserRoleService roleService;
	
	@Bean 
	public final Party getCustomerEntityBean() {

		UserRole ur = roleService.findByName("Customer");
	
		final User user = new User();
		user.setEnabled(true);
		user.setUsername("mackdad");
		user.setPassword("mackdad1234");
		user.getUserRoles().add(ur);
		
		final PersonEntity person = new PersonEntity();
		
		person.setFamilyName("Test Family Name");
		person.setGivenName("Test Given Name");

		final CustomerEntity partyRole = new CustomerEntity();
		partyRole.setRoleStart(new Date());
		partyRole.setCustomerNumber("9832145731");
		
		person.addRole(partyRole);
		partyRole.setRoleParty(person);
		
		user.setParty(person);
		person.addUser(user);
		
		return person;
	}
	
}
