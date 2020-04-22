package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.person.IPersonService;
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

    }
	
	@Autowired
	@Qualifier(value="mochiTransactionManager")
	private PlatformTransactionManager txManager;
    
	@Autowired
    private AuthenticationManager am;
	
	//ensure the mochiEntityManagerFactory not the security EM
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
    private IPersonService personService;
	
	@Autowired
	private PartyEntityBeanFactory partyEntityBeanFactory;
	
	private io.nzbee.entity.party.Party customer = null;
	
	@BeforeTransaction
    public void setUp() { 
    	new TransactionTemplate(txManager).execute(status -> {

    		customer = partyEntityBeanFactory.getCustomerEntityBean();
	    	
    	    //persist a new transient test category
    	    entityManager.persist(customer);
    	    entityManager.flush();
    	    entityManager.close();

            return null;
        });
    }
	
	@AfterTransaction
	public void cleanUp() {
	    new TransactionTemplate(txManager).execute(status -> {
	        // Check if the entity is managed by EntityManager.
	        // If not, make it managed with merge() and remove it.
	    	entityManager.remove(entityManager.contains(customer) ? customer : entityManager.merge(customer));
	        return null;
	    });
	}
    
    protected void login(String name, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(name, password);
        SecurityContextHolder.getContext().setAuthentication(am.authenticate(auth));
    }
    
	
	@Test
	@Transactional
	@WithUserDetails(value = "mackdad")
    public void whenFindByUsernameAndRole_thenReturnParty() {
		 
		//login("mackdad", "mackdad1234");
	    
		// when
	    Party found = personService.findByUsernameAndRole("mackdad", Customer.class).get();
     
	    // then
	    assertFound(found);
	    	
    }

	
    private void assertFound(final Party found) {
    	
	    assertThat(((Person) found).getGivenName())
	    .isEqualTo("Test Given Name");
	    assertThat(((Person) found).getFamilyName())
	    .isEqualTo("Test Family Name");
    }
	
    
	
}
