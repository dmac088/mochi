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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.person.Person;
import io.nzbee.entity.role.customer.Customer;
import io.nzbee.security.user.User;
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
    private AuthenticationManager am;
	
	//ensure the mochiEntityManagerFactory not the security EM
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	@Qualifier("userRoleService")
	private IUserRoleService userRoleService;
	
	private io.nzbee.security.user.User user = null;
	
    @Before
    public void setUp() { 
    	this.persistNewUser();
    }
    
    
    @After
    public void clear() {
        SecurityContextHolder.clearContext();
    }
    
    protected void login(String name, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password);
        SecurityContextHolder.getContext().setAuthentication(am.authenticate(auth));
    }
    
	public User persistNewUser() {
    	
		final Person person = new Person();
		
		person.setFamilyName("Test Family Name");
		person.setGivenName("Test Given Name");

		final Customer partyRole = new Customer();
		partyRole.setRoleStart(new Date());
		
		person.addRole(partyRole);
		partyRole.setRoleParty(person);
		
		user = new User();
		user.setUsername("testusername@testdomain.com");
		user.setPassword("test1234");
		
		person.setPartyUser(user);
		user.setUserParty(person);
		
		user.addUserRole(userRoleService.findByName("Customer"));
		
	    //persist a new transient test category
	    entityManager.persist(user);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return user;
	}
	
	
	@Test
    public void whenFindById_thenReturnParty() {
//		login("admin", "admin1234");
//		
//		user = (User) userService.loadUserByUsername("testusername@testdomain.com");
//		
//        // when
//    	Party found = user.getUserParty();
//     
//        // then
//    	assertFound(found);
    }

	
//	@Test
//    public void whenFindByRoleName_thenReturnAllParties() {
//		login("admin", "admin1234");
//        // when
//    	List<Party> found = partyService.findByRoleType(Customer.class);
//     
//        // then
//    	found.stream().filter(f -> f.getPartyId().equals(customer.getPartyId())).forEach(p -> assertFound(p));
//    	
//    }
	
    private void assertFound(final Party found) {
    	
	    assertThat(((Person) found).getGivenName())
	    .isEqualTo("Test Given Name");
	    assertThat(((Person) found).getFamilyName())
	    .isEqualTo("Test Family Name");
    }
	
    @After
    public void closeConnection() {
    	entityManager.close();
    }
	
	
	
}
