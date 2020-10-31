package io.nzbee.entity.adapters;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.nzbee.Constants;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.ICustomerPortService;
import io.nzbee.dto.customer.CustomerDTOIn;
import io.nzbee.entity.bag.Bag;
import io.nzbee.entity.bag.IBagService;
import io.nzbee.entity.party.person.IPersonMapper;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.Person;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.role.IRoleTypeRepository;
import io.nzbee.exceptions.customer.CustomerAlreadyExistException;
import io.nzbee.exceptions.customer.CustomerException;
import io.nzbee.exceptions.customer.CustomerNotFoundException;
import io.nzbee.exceptions.customer.CustomerPasswordsDoNotMatchException;
import io.nzbee.exceptions.product.ProductException;
import io.nzbee.security.authority.Authority;
import io.nzbee.security.user.IUserService;
import io.nzbee.security.user.User;
import io.nzbee.security.user.role.IUserRoleService;
import io.nzbee.security.user.role.UserRole;
import io.nzbee.security.user.verificationtoken.VerificationToken;
import io.nzbee.security.user.verificationtoken.VerificationTokenRepository;

@Component
public class PostgresCustomerAdapter implements ICustomerPortService {

	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IPersonMapper personMapper;
	
	@Autowired
	private IRoleTypeRepository roleTypeRepository;
	
	@Autowired
	private IUserRoleService userRoleService;
	
	@Autowired
    private IUserService userService;
	
	@Autowired
    private IBagService bagService;
	
	@Autowired
    private IProductService productService;
	
	@Autowired
    private VerificationTokenRepository tokenRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Customer findByUsername(String userName) {
		Person p = personService.findByUsernameAndRole(userName, io.nzbee.entity.role.customer.Customer.class)
				.orElseThrow(() -> new CustomerNotFoundException("Customer with username " + userName + " not found!"));
		return personMapper.entityToDo(p);
	}

	@Override
	@Transactional
	public Customer registerNewCustomer(CustomerDTOIn customer) {
		boolean exists = personService.userExists(customer.getUserName(), io.nzbee.entity.role.customer.Customer.class);
		if(exists) {
			throw new CustomerAlreadyExistException("Customer with username " + customer.getUserName() + " already exists!");
		}
		
		Customer c = new Customer(	customer.getGivenName(),
									customer.getFamilyName(),
									customer.getUserName(),
									customer.getCustomerId(),
									customer.isEnabled());
		
		if(!customer.getPassword().equals(customer.getConfirmPassword())) {
			throw new CustomerPasswordsDoNotMatchException("Customer passwords do not match");
		}
		
		c.setPassword(customer.getPassword(), customer.getConfirmPassword());
		this.save(c);
		return c;
	}
	
	@Override
	@Transactional
	public void save(Customer domainObject) {
		//in our domain world a customer is a person
		
		UserRole ur = userRoleService.findByName("Customer");
		User u = new User();
		u.addUserRole(ur);
		ur.addUser(u);
		u.setUsername(domainObject.getUserName());
		u.setPassword(domainObject.getPassword());
		u.setEnabled(false);
		u.setUsing2FA(false);
		
		io.nzbee.entity.role.customer.Customer c = new io.nzbee.entity.role.customer.Customer();
		c.setCustomerNumber(domainObject.getCustomerID());
		c.setRoleStart(new Date());
		
		io.nzbee.entity.role.RoleType roleType = roleTypeRepository.findByRoleTypeDesc(io.nzbee.entity.role.customer.Customer.class.getSimpleName()).get();
		c.setRoleType(roleType);
		
		Person p = new Person();
		p.setGivenName(domainObject.getGivenName());
		p.setFamilyName(domainObject.getFamilyName());
		p.setEnabled(domainObject.isEnabled());
		
		p.setUser(u);
		u.setParty(p);
		p.addRole(c);
		c.setRoleParty(p);
		
		personService.save(p);
	}
	
	
	@Override
	@Transactional
	public void update(Customer domainObject) {
		//in our domain world a customer is a person
		Person p = personService.findByUsernameAndRole(domainObject.getUserName(), io.nzbee.entity.role.customer.Customer.class).get();
		
		p.setGivenName(domainObject.getGivenName());
		p.setFamilyName(domainObject.getFamilyName());
		
		personService.save(p);
	}


	@Override
	@Transactional
	public void delete(Customer object) {
		Person t = personService.findByUsernameAndRole(object.getUserName(), io.nzbee.entity.role.customer.Customer.class)
				.orElseThrow(() -> new CustomerException("Customer with username " + object.getUserName() + " not found!"));
		personService.delete(t);
		
	}

	@Override
	@Transactional
	public void update(CustomerDTOIn dto) {
		Customer customerDo = new Customer(	dto.getGivenName(),
											dto.getFamilyName(),
											dto.getUserName(),
											dto.getCustomerId(),
											dto.isEnabled());

		customerDo.setPassword(dto.getPassword(), dto.getConfirmPassword());
		
		this.update(customerDo);
	}

	@Override
	@Transactional
	public void delete(String userName) {
		Person t = personService.findByUsernameAndRole(userName, io.nzbee.entity.role.customer.Customer.class)
				.orElseThrow(() -> new CustomerException("Customer with username " + userName + " not found!"));
		personService.delete(t);
	}

	@Override
	public void addCustomerLocation(Customer c, String clientIP) {
		UserDetails u = userService.findUserByEmail(c.getUserName());
		userService.addUserLocation((User) u, clientIP);
	}

	@Override
	@Transactional
	public String validateVerificationToken(String token) {
		final VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return Constants.TOKEN_INVALID;
        }

        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate()
            .getTime() - cal.getTime()
            .getTime()) <= 0) {
            tokenRepository.delete(verificationToken);
            return Constants.TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        // tokenRepository.delete(verificationToken);
        userService.saveRegisteredUser(user);
        return Constants.TOKEN_VALID;
	}

	@Override
	@Transactional(readOnly = true)
	public Customer getCustomer(String verificationToken) {
		final VerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            User u = token.getUser();
            Optional<Person> p = personService.findByUsernameAndRole(u.getUsername(), io.nzbee.entity.role.customer.Customer.class);
            return personMapper.entityToDo(p.get());
        }
        return null;
	}
	
	@Override
	@Transactional
	public void authWithoutPassword(String token) {
		
		User user = userService.getUser(token);

        List<Authority> authorities = user.getUserRoles()
                .stream()
                .map(r -> r.getAuthorities())
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

	@Override
	@Transactional
	public void addItemToBag(Customer c, BagItem bagItem) {
		
		//get the party of the bag, which will be a person
		Person t = personService.findByUsernameAndRole(c.getUserName(), io.nzbee.entity.role.customer.Customer.class)
					.orElseThrow(() -> new CustomerException("Customer with username " + c.getUserName() + " not found!"));
		
		
		//get the product of the item the customer wishes to add
		String upc = bagItem.getProduct().getProductUPC();
		ProductEntity p = productService.findByCode(upc)
					.orElseThrow(() -> new ProductException("Customer with UPC " + upc + " not found!"));
		
		
		//get the bag of the person
		Optional<Bag> ob = bagService.findByCode(c.getUserName());
		
		Bag b = (ob.isPresent()) 
				? ob.get()
				: new Bag();
		
		io.nzbee.entity.bag.item.BagItem bi = new io.nzbee.entity.bag.item.BagItem(p);
		bi.setQuantity(bagItem.getQuantity());
		b.addItem(bi);
		
		//set the bag / party relationship
		t.setBag(b);
		b.setParty(t);
				
		bagService.save(b);
	}
}
