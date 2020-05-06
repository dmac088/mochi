package io.nzbee.entity.adapters;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.ICustomerPortService;
import io.nzbee.dto.customer.CustomerDTO;
import io.nzbee.entity.party.person.IPersonMapper;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.Person;
import io.nzbee.entity.role.IRoleTypeRepository;
import io.nzbee.exceptions.customer.CustomerException;
import io.nzbee.security.user.User;

@Component
public class PostgresCustomerAdapter implements ICustomerPortService {

	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IPersonMapper personMapper;
	
	@Autowired
	private IRoleTypeRepository roleTypeRepository;
	
	@Override
	public Customer findByUsername(String userName) {
		Person p = personService.findByUsernameAndRole(userName, io.nzbee.entity.role.customer.Customer.class)
				.orElseThrow(() -> new CustomerException("Customer with username " + userName + " not found!"));
		return personMapper.entityToDo(p);
	}

	@Override
	public void registerNewCustomer(CustomerDTO customer) {
		Customer c = new Customer(	customer.getGivenName(),
									customer.getFamilyName(),
									customer.getUserName(),
									customer.getCustomerId(),
									customer.isEnabled());
		c.setPassword(customer.getPassword(), customer.getConfirmPassword());
		this.save(c);
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
		
		
		io.nzbee.entity.role.RoleType roleType = roleTypeRepository.findByRoleTypeDesc(io.nzbee.entity.role.customer.Customer.class.getSimpleName()).get();
		c.setRoleType(roleType);
		
		Person p = new Person();
		p.setGivenName(domainObject.getGivenName());
		p.setFamilyName(domainObject.getFamilyName());
		p.setEnabled(domainObject.isEnabled());
		
		p.setPartyUser(u);
		u.setUserParty(p);
		p.addRole(c);
		c.setRoleParty(p);
		
		personService.save(p);
	}
	
	@Override
	public void updateCustomer(CustomerDTO dto) {
		Customer customerDo = new Customer(	dto.getGivenName(),
						    				dto.getFamilyName(),
						    				dto.getUserName(),
						    				dto.getCustomerId(),
						    				dto.isEnabled());
		
		customerDo.setPassword(dto.getPassword(), dto.getConfirmPassword());
		
		this.update(customerDo);
	}
	
	@Override
	public void update(Customer domainObject) {
		//in our domain world a customer is a person
		Person p = personService.findByUsernameAndRole(domainObject.getUserName(), io.nzbee.entity.role.customer.Customer.class).get();
		
		p.setGivenName(domainObject.getGivenName());
		p.setFamilyName(domainObject.getFamilyName());
		
		personService.save(p);
	}
	

	@Override
	public void deleteCustomer(String userName) {
		Person t = personService.findByUsernameAndRole(userName, io.nzbee.entity.role.customer.Customer.class)
				.orElseThrow(() -> new CustomerException("Customer with username " + userName + " not found!"));
		personService.delete(t);
	}
	

}
