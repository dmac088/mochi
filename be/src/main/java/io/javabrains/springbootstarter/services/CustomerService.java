package io.javabrains.springbootstarter.services;

import java.util.ArrayList;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.javabrains.springbootstarter.domain.Party;
import io.javabrains.springbootstarter.domain.PartyPerson;
import io.javabrains.springbootstarter.domain.PartyPersonRepository;
import io.javabrains.springbootstarter.domain.PartyRepository;
import io.javabrains.springbootstarter.domain.Role;
import io.javabrains.springbootstarter.domain.RoleCustomer;
import io.javabrains.springbootstarter.errors.CustomerAlreadyExistException;
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
    
    
    // API
    @Override
	@Transactional
    public Party registerNewPersonCustomer(final PartyPerson person) {
        if (customerExist(person.getPartyUser().getUsername())) {
            throw new CustomerAlreadyExistException("There is an account with that username: " + person.getPartyUser().getUsername());
        }
        
		//create the role object
		person.setPartyRoles(new ArrayList<Role>());
		RoleCustomer c1 = new RoleCustomer();
		c1.setRoleStart(new Date());
		
		//add the role to person
		person.addRole(c1);
		
		//add the person to role
		c1.setRoleParty(person);
		
		//add the user object
		person.getPartyUser().setUserRoles(new ArrayList<UserRole>());
		person.getPartyUser().addUserRole(userRoleService.loadUserRoleByRoleName("CUSTOMER"));
		person.getPartyUser().setEnabled(true);
		
		//persist the parent
		return personRepository.save(person);
    }

    @Override
    public boolean customerExist(final String username) {
        return partyRepository.findByPartyUserUsername(username).isPresent();
        
    }

    
}