package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.Person;
import io.nzbee.entity.role.customer.Customer;
import io.nzbee.security.user.User;
import io.nzbee.security.user.UserService;
import io.nzbee.security.user.role.IUserRoleService;
import io.nzbee.security.user.role.UserRole;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
@SqlGroup({
	@Sql(scripts = "/database/mochi_schema.sql",
			config = @SqlConfig(dataSource = "mochiDataSourceOwner", 
			transactionManager = "mochiTransactionManagerOwner",
			transactionMode = TransactionMode.ISOLATED)), 
	@Sql(scripts = "/database/mochi_data.sql",
			config = @SqlConfig(dataSource = "mochiDataSource", 
			transactionManager = "mochiTransactionManager",
			transactionMode = TransactionMode.ISOLATED)),
	@Sql(scripts = "/database/security_schema.sql",
			config = @SqlConfig(dataSource = "securityDataSourceOwner", 
			transactionManager = "securityTransactionManagerOwner",
			transactionMode = TransactionMode.ISOLATED)), 
	@Sql(scripts = "/database/security_data.sql",
			config = @SqlConfig(dataSource = "securityDataSource", 
			transactionManager = "securityTransactionManager",
			transactionMode = TransactionMode.ISOLATED))
})
//@ContextConfiguration(classes = {UserRoleService.class}) 
public class IT_UserEntityRepositoryIntegrationTest {
  
	@Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager am;
	
	//ensure the mochiEntityManagerFactory not the security EM
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	private IPersonService personService;

	
	private io.nzbee.security.user.User user = null;
    
    @After
    public void clear() {
    	entityManager.close();
        SecurityContextHolder.clearContext();
    }
    
    @Before
    public void setup() {
    	persistNewUser();
    }
    
    protected void login(String usernname, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(usernname, password);
        SecurityContextHolder.getContext().setAuthentication(am.authenticate(auth));
    }
    
    
	public void persistNewUser() {
    	
		final Person person = new Person();
		
		person.setFamilyName("Test Family Name");
		person.setGivenName("Test Given Name");

		final Customer partyRole = new Customer();
		partyRole.setRoleStart(new Date());
		
		person.addRole(partyRole);
		partyRole.setRoleParty(person);
		
		user = new User();
		user.setUsername("testusername");
		user.setPassword("test1234");
		user.setUserParty(person);
		user.setEnabled(true);
	
		
		final UserRole userRole = userRoleService.findByName("Customer");
		user.addUserRole(userRole);
		
	    //persist a new transient test category
	    entityManager.persist(user);
	    entityManager.flush();
	}
	
	
	@Test
	@Rollback(true)
    public void whenFindByUsername_thenReturnUser() {
		login("admin", "admin1234");
		
        // when
    	Person found = personService.findByUsernameAndRole("testusername", Customer.class).get();
     
        // then
    	assertFound(found);
    }

	@Test
	@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userService")
	public void whenFindByExistingUsername_thenExistingUser() {
		UserDetails ud = userService.loadUserByUsername("admin");
		
		assertAdminFound(((User) ud).getUserParty());
	}
	
    private void assertFound(final Party found) {
    	
	    assertThat(((Person) found).getGivenName())
	    .isEqualTo("Test Given Name");
	    assertThat(((Person) found).getFamilyName())
	    .isEqualTo("Test Family Name");
    }
    
    private void assertAdminFound(final Party found) {
    	
	    assertThat(((Person) found).getGivenName())
	    .isEqualTo("Daniel");
	    assertThat(((Person) found).getFamilyName())
	    .isEqualTo("Mackie");
    }

	
	
}
