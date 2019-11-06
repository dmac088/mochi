package io.nzbee.dto.customer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.nzbee.dto.IDto;
import io.nzbee.entity.party.IPartyService;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.Person;
import io.nzbee.entity.role.Role;
import io.nzbee.exceptions.CustomerAlreadyExistException;
import io.nzbee.security.user.User;
import io.nzbee.security.user.role.IUserRoleService;
import io.nzbee.security.user.role.UserRole;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private IPartyService partyService;
    
    @Autowired
    private IPersonService personService;
    
    @Autowired
    private IUserRoleService userRoleService;
    
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
	public List<Customer> findAll() {
		List<Customer> cl = new ArrayList<Customer>();
		List<Party> pl = partyService.findAll();
		for(Party p : pl) {
			Customer c = new Customer();
			c.setCustomerID(((io.nzbee.entity.role.customer.Customer)p.getPartyRole(PARTY_ROLE_NAME)).getCustomerNumber());
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
	public Optional<Customer> findByCode(String userName) {
		Optional<Party> pr1 = partyService.findByCode(userName);
		Customer c1 = convertToCustomerDO(pr1.get());
		return Optional.ofNullable(c1);
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
		io.nzbee.entity.role.customer.Customer c1 = new io.nzbee.entity.role.customer.Customer();
		c1.setRoleStart(new Date());
		//c1.setCustomerNumber(customer.getCustomerID());
		
		//create a new user object
		User u1 = new User();
		u1.setUsername(customer.getUserName());
		u1.setEnabled(true);
		u1.setUserRoles(new HashSet<UserRole>());
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
		personService.save(p1);
    }  

    @Override
    public boolean customerExist(final String username) {
        return partyService.findByCode(username).isPresent();
    }

	@Override
	@Transactional
	public void deleteCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Party p = partyService.findByCode(customer.getUserName()).get();
		partyService.delete(p);
	}
	
	@Override
	@Transactional
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Party p = partyService.findByCode(customer.getUserName()).get();
		//partyRepository.delete(p);
		
		Person pp = null;
		if(customer.getPartyType().equals(Person.class.getSimpleName())) {
			pp = (Person) p;
			pp.setGivenName(customer.getGivenName());
			pp.setFamilyName(customer.getFamilyName());
			personService.save(pp);
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


	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<Customer> findById(long brandId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<Customer> findByDesc(String desc) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Customer> findAll(List<IDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Customer entityToDTO(io.nzbee.entity.role.customer.Customer entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Customer doToDto(io.nzbee.domain.customer.Customer domainObject) {
		// TODO Auto-generated method stub
		return null;
	}


}