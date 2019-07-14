package io.nzbee.services;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.nzbee.domain.Customer;
import io.nzbee.entity.customer.RoleCustomer;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.PartyRepository;
import io.nzbee.entity.person.Person;
import io.nzbee.entity.person.PersonRepository;
import io.nzbee.entity.role.Role;
import io.nzbee.exceptions.CustomerAlreadyExistException;
import io.nzbee.security.User;
import io.nzbee.security.UserRole;
import io.nzbee.security.UserRoleService;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    private PartyRepository partyRepository;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
	@Qualifier("userPasswordEncoder")
	private PasswordEncoder passwordEncoder;
	
	
	private final String USER_ROLE_NAME		= "Customer";
	private final String PARTY_ROLE_NAME	= "Customer";
    
    // API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		List<Customer> cl = new ArrayList<Customer>();
		List<Party> pl = partyRepository.findAll();
		for(Party p : pl) {
			Customer c = new Customer();
			c.setCustomerID(((RoleCustomer)p.getPartyRole(PARTY_ROLE_NAME)).getCustomerNumber());
			c.setGivenName(((Person)p).getGivenName());
			c.setFamilyName(((Person)p).getFamilyName());
			c.setUserName(p.getPartyUser().getUsername());
			c.setPassword(p.getPartyUser().getPassword());
			cl.add(c);
		}
		return cl;
	}
	
	
	@Override
	@Transactional
	public Customer getCustomer(String userName) {
		Optional<Party> pr1 = partyRepository.findByPartyUserUsername(userName);
		Customer c1 = new Customer();
		c1.setGivenName(((Person)pr1.get()).getGivenName());
		c1.setFamilyName(((Person)pr1.get()).getFamilyName());
		c1.setUserName(((Person)pr1.get()).getPartyUser().getUsername());
		c1.setCustomerID(((RoleCustomer)((Person)pr1.get()).getPartyRole(PARTY_ROLE_NAME)).getCustomerNumber());
		c1.setPassword(pr1.get().getPartyUser().getPassword());
		c1.setMatchingPassword(pr1.get().getPartyUser().getPassword());
		c1.setPartyType(Person.class.getSimpleName());
		return c1;
	}
	
	
    @Override
	@Transactional
    public void registerNewCustomer(final Customer customer) {
        if (customerExist(customer.getUserName())) {
            throw new CustomerAlreadyExistException("There is an account with that username: " + customer.getUserName());
        }
        
        Person p1 = new Person();
        p1.setGivenName(customer.getGivenName());
        p1.setFamilyName(customer.getFamilyName());
        
		//create the role object
		p1.setPartyRoles(new ArrayList<Role>());
		RoleCustomer c1 = new RoleCustomer();
		c1.setRoleStart(new Date());
		//c1.setCustomerNumber(customer.getCustomerID());
		
		//create a new user object
		User u1 = new User();
		u1.setUsername(customer.getUserName());
		u1.setEnabled(true);
		u1.setUserRoles(new ArrayList<UserRole>());
		u1.addUserRole(userRoleService.loadUserRoleByRoleName(USER_ROLE_NAME));
		u1.setPassword(customer.getPassword());
		
		//add user to person 
		p1.addUser(u1);
		
		//addPartytoUser
		u1.setUserParty(p1);
		
		//add the role to person
		p1.addRole(c1);
		
		//add the person to role
		c1.setRoleParty(p1);
		 
		//add role to person
		p1.addRole(c1);
		c1.setRoleParty(p1);
		
		//persist the parent
		personRepository.save(p1);
    }  

    @Override
    public boolean customerExist(final String username) {
        return partyRepository.findByPartyUserUsername(username).isPresent();
    }

	@Override
	@Transactional
	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Party p = partyRepository.findByPartyUserUsername(customer.getUserName()).get();
		//partyRepository.delete(p);
		System.out.println("PartyId = " + p.getPartyId());
		partyRepository.deleteById(p.getPartyId());
	}
	
	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Party p = partyRepository.findByPartyUserUsername(customer.getUserName()).get();
		//partyRepository.delete(p);
		
		Person pp = null;
		if(customer.getPartyType().equals(Person.class.getSimpleName())) {
			System.out.println("pop");
			pp = (Person) p;
			pp.setGivenName(customer.getGivenName());
			pp.setFamilyName(customer.getFamilyName());
			personRepository.save(pp);
		}
		
	}

	@Override
	public Customer convertToCustomerDO(Party party) {
		final Customer cDo = new Customer();
		if (Person.class.getSimpleName().equals(party.getClass().getSimpleName())) {
			Person person = (Person) party;
	    	cDo.setGivenName(person.getGivenName());
	    	cDo.setFamilyName(person.getFamilyName());
	    	cDo.setPassword(person.getPartyUser().getPassword());
	    	cDo.setUserName(person.getPartyUser().getUsername());
	    	cDo.setMatchingPassword(person.getPartyUser().getPassword());
	    	cDo.setPartyType(party.getClass().getSimpleName());
		}
		return cDo;
	}

    
}