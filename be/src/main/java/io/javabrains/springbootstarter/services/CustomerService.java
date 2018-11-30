package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.javabrains.springbootstarter.domain.Party;
import io.javabrains.springbootstarter.domain.PartyPerson;
import io.javabrains.springbootstarter.domain.PartyPersonRepository;
import io.javabrains.springbootstarter.domain.PartyRepository;
import io.javabrains.springbootstarter.domain.Role;
import io.javabrains.springbootstarter.domain.RoleCustomer;
import io.javabrains.springbootstarter.errors.CustomerAlreadyExistException;
import io.javabrains.springbootstarter.security.User;
import io.javabrains.springbootstarter.security.UserRole;
import io.javabrains.springbootstarter.security.UserRoleService;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    private PartyRepository partyRepository;
    
    @Autowired
    private PartyPersonRepository personRepository;
    
    @Autowired
    private UserRoleService userRoleService;
    
	@Autowired
	@Qualifier("userPasswordEncoder")
	private PasswordEncoder passwordEncoder;
	
	private final String USER_ROLE_NAME		= "CUSTOMER";
	private final String PARTY_ROLE_NAME	= "Customer";
    
    // API
    //This method should accept a DTO and return a DTO
    //The DTO is coarse grained and contains a flat structure of properties
    //if we did not use a DTO we would have JSON nesting as per the domain model structure, which is hard to manage in our client views
    //The DTO is simple and dumb, it is the service layer that manages the translation between DTO and domain objects
    
	@Override
	@Transactional
	public List<CustomerDTO> getCustomers() {
		
		
		return null;
	}
	
	
	@Override
	@Transactional
	public CustomerDTO getCustomer(String userName) {
		Optional<Party> pr1 = partyRepository.findByPartyUserUsername(userName);
		CustomerDTO c1 = new CustomerDTO();
		c1.setGivenName(((PartyPerson)pr1.get()).getGivenName());
		c1.setFamilyName(((PartyPerson)pr1.get()).getFamilyName());
		c1.setUserName(((PartyPerson)pr1.get()).getPartyUser().getUsername());
		c1.setCustomerID(((RoleCustomer)((PartyPerson)pr1.get()).getPartyRole(PARTY_ROLE_NAME)).getCustomerNumber());
		return c1;
	}
	
	
    @Override
	@Transactional
    public void registerNewCustomer(final CustomerDTO customer) {
        if (customerExist(customer.getUserName())) {
            throw new CustomerAlreadyExistException("There is an account with that username: " + customer.getUserName());
        }
        
        PartyPerson p1 = new PartyPerson();
        p1.setGivenName(customer.getGivenName());
        p1.setFamilyName(customer.getFamilyName());
        
        
		//create the role object
		p1.setPartyRoles(new ArrayList<Role>());
		RoleCustomer c1 = new RoleCustomer();
		c1.setRoleStart(new Date());
		
		//create a new user object
		User u1 = new User();
		u1.setUsername(customer.getUserName());
		u1.setEnabled(true);
		u1.setUserRoles(new ArrayList<UserRole>());
		u1.addUserRole(userRoleService.loadUserRoleByRoleName(USER_ROLE_NAME));
		u1.setPassword(passwordEncoder.encode(customer.getPassword()));
		
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

    
}