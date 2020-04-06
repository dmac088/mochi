package io.nzbee.test.integration.beans;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.person.Person;
import io.nzbee.entity.role.customer.Customer;

@Service(value = "partyEntityBeanFactory")
@Profile(value = "dev")
public class PartyEntityBeanFactory {

	@Bean 
	public final Party getCustomerEntityBean() {
		
		final Person person = new Person();
		
		person.setFamilyName("Test Family Name");
		person.setGivenName("Test Given Name");

		final Customer partyRole = new Customer();
		partyRole.setRoleStart(new Date());
		
		person.addRole(partyRole);
		partyRole.setRoleParty(person);
		
		return person;
	}
	
}
