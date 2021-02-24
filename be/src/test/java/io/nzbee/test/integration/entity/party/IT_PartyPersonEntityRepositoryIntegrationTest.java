package io.nzbee.test.integration.entity.party;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.entity.role.customer.CustomerEntity;
import io.nzbee.test.integration.entity.beans.party.IPartyEntityBeanFactory;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
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
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	@Autowired
    private IPersonService personService;
	
	@Autowired
	private IPartyEntityBeanFactory partyEntityBeanFactory;
	
	private static Party customer = null;
	
	private static boolean setUpIsDone = false;
	
	@Before
	public void setUp() {
		if (setUpIsDone) {
			return;
		}
		try (Connection con = database.getConnection()) {
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_schema.sql"));
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_data.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.persistNewCustomer();
		setUpIsDone = true;
	}
	

    public void persistNewCustomer() { 
		
		customer = partyEntityBeanFactory.getBean();
	    	
   	    entityManager.persist(customer);
   
    }
	
    //as long as the admin account can fetch the new user, we know that it was persisted properly by hibernate
	@Test
	@Rollback(false)
	@WithUserDetails(value = "admin")
    public void whenFindByUsernameAndRole_thenReturnParty() {
		
		// when
	    Optional<PersonEntity> found = personService.findByUsernameAndRole("mackdad", "Customer");
     
	    // then
	    assertFound(found);
	    	
    }

	
    private void assertFound(Optional<PersonEntity> found) {
    	assertNotNull(found);
    	assertTrue(found.isPresent());
    	
	    assertThat(((PersonEntity) found.get()).getGivenName())
	    .isEqualTo("Test Given Name");
	    assertThat(((PersonEntity) found.get()).getFamilyName())
	    .isEqualTo("Test Family Name");
	    assertThat(((PersonEntity) found.get()).getUser().getUsername())
	    .isEqualTo("mackdad");
	    assertThat(((PersonEntity) found.get()).getUser().getUserRoles().stream().findFirst().get().getName())
	    .isEqualTo("Customer");
	    assertThat(((CustomerEntity)(((PersonEntity) found.get()).getPartyRoles().stream().findFirst().get())).getCustomerNumber())
	    .isEqualTo("9832145731");
	    
    }
	
    
	
}
