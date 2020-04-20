package io.nzbee.entity.adapters;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.ICustomerPortService;
import io.nzbee.entity.party.person.IPersonMapper;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.Person;
import io.nzbee.security.user.User;

@Component
public class PostgresCustomerAdapter implements ICustomerPortService {

	@Autowired
	IPersonService personService;
	
	@Autowired
	IPersonMapper personMapper;
	
	@Override
	public Optional<Customer> findByUsername(String userName) {
		return Optional.ofNullable(personMapper.entityToDo(personService.findByUsernameAndRole(userName, Customer.class).get()));
	}

	@Override
	public void save(Customer domainObject) {
		//in our domain world a customer is a person
		
		User u = new User();
		u.setUsername(domainObject.getUserName());
		u.setPassword(domainObject.getPassword());
		u.setEnabled(true);
		
		io.nzbee.entity.role.customer.Customer c = new io.nzbee.entity.role.customer.Customer();
		c.setCustomerNumber(domainObject.getCustomerID());
		c.setRoleStart(new Date());
	
		Person p = new Person();
		p.setGivenName(domainObject.getGivenName());
		p.setFamilyName(domainObject.getFamilyName());
		
		p.setPartyUser(u);
		u.setUserParty(p);
		p.addRole(c);
		c.setRoleParty(p);
		
		personService.save(p);
	}

	

}
