package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithUserDetails;
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
import io.nzbee.test.integration.beans.PartyEntityBeanFactory;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
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
public class IT_PartyPersonEntityRepositoryIntegrationTest {

	
	@TestConfiguration
    static class PartyServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test

	}
	
	@MockBean
    private JavaMailSender mailSender;
	
	//ensure the mochiEntityManagerFactory not the security EM
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
    private IPersonService personService;
	
	@Autowired
	private PartyEntityBeanFactory partyEntityBeanFactory;
	

	@Before
    public void setUp() { 

		Party customer = partyEntityBeanFactory.getCustomerEntityBean();
	    	
   	    entityManager.persist(customer);
   
    }
	
    //as long as the admin account can fetch the new user, we know that it was persisted properly by hibernate
	@Test
	@WithUserDetails(value = "admin")
    public void whenFindByUsernameAndRole_thenReturnParty() {
		
		// when
	    Optional<Person> found = personService.findByUsernameAndRole("mackdad", Customer.class);
     
	    // then
	    assertFound(found);
	    	
    }

	
    private void assertFound(Optional<Person> found) {
    	assertNotNull(found);
    	assertTrue(found.isPresent());
    	
	    assertThat(((Person) found.get()).getGivenName())
	    .isEqualTo("Test Given Name");
	    assertThat(((Person) found.get()).getFamilyName())
	    .isEqualTo("Test Family Name");
	    assertThat(((Person) found.get()).getUser().getUsername())
	    .isEqualTo("mackdad");
	    assertThat(((Person) found.get()).getUser().getUserRoles().stream().findFirst().get().getName())
	    .isEqualTo("Customer");
	    assertThat(((Customer)(((Person) found.get()).getPartyRoles().stream().findFirst().get())).getCustomerNumber())
	    .isEqualTo("9832145731");
	    
    }
	
    
	
}
