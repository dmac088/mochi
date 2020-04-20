package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
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
import io.nzbee.entity.party.IPartyService;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.PartyServiceImpl;
import io.nzbee.entity.party.person.Person;
import io.nzbee.entity.role.customer.Customer;
import io.nzbee.test.integration.beans.PartyEntityBeanFactory;


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
public class IT_PartyEntityRepositoryIntegrationTest {

	
	@TestConfiguration
    static class PartyServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
        @Bean(value = "partyService")
        public IPartyService partyService() {
            return new PartyServiceImpl();
        }
        
        @Bean(value = "partyEntityBeanFactory")
        public PartyEntityBeanFactory partyFactoryBean() {
            return new PartyEntityBeanFactory();
        }

    }
    
	@Autowired
    private AuthenticationManager am;
	
	//ensure the mochiEntityManagerFactory not the security EM
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
    private IPartyService partyService;
	
	@Autowired
	private PartyEntityBeanFactory partyEntityBeanFactory;
	
	private io.nzbee.entity.party.Party customer = null;
	
    @Before
    public void setUp() { 
    	this.persistNewCustomer();
    }
    
    
    @After
    public void clear() {
        SecurityContextHolder.clearContext();
    }
    
    protected void login(String name, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password);
        SecurityContextHolder.getContext().setAuthentication(am.authenticate(auth));
    }
    
	private Party persistNewCustomer() {
    	
		customer = partyEntityBeanFactory.getCustomerEntityBean();
	    	
	    //persist a new transient test category
	    entityManager.persist(customer);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return customer;
	}
	
	
	@Test
    public void whenFindById_thenReturnParty() {
		login("admin", "admin1234");
		
        // when
    	Party found = partyService.findById(customer.getPartyId()).get();
     
        // then
    	assertFound(found);
    }

	
	@Test
    public void whenFindByRoleName_thenReturnAllParties() {
		login("admin", "admin1234");
        // when
    	List<Party> found = partyService.findByRoleType(Customer.class);
     
        // then
    	found.stream().filter(f -> f.getPartyId().equals(customer.getPartyId())).forEach(p -> assertFound(p));
    	
    }
	
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
