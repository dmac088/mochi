package io.nzbee.test.integration.entity.party;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.party.address.IPartyAddressService;
import io.nzbee.entity.party.address.PartyAddressDTO;
import io.nzbee.entity.party.address.PartyAddressEntity;
import io.nzbee.test.integration.entity.beans.party.address.IPartyAddressEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class IT_PartyAddressEntityRepositoryIntegrationTest {

	@TestConfiguration
    static class PartyAddressEntityRepositoryIntegrationTest {
       
    }
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
 
    @Autowired
    private IPartyAddressService partyAddressService;
    
    @Autowired
    private IPartyAddressEntityBeanFactory partyAddressEntityBeanFactory;
    
    private static PartyAddressEntity p = null;
    
    private static boolean setUpIsDone = false;
    
	public PartyAddressEntity persistNewPartyAddress() {
    
		p = partyAddressEntityBeanFactory.getBean();
	    
	    entityManager.persist(p);
	    	
	    return p;
	}
	
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
		this.persistNewPartyAddress();
		setUpIsDone = true;
	}
	
	@Test
	@Rollback(false)
	@WithUserDetails(value = "admin")
	public void whenFindById_thenReturnPartyAddress() {
		 // when
    	Optional<PartyAddressEntity> found = partyAddressService.findById(p.getAddressId());
     
        // then
    	assertFoundEntity(found);
	}
	
	@Test
	@Rollback(false)
	@WithUserDetails(value = "admin")
	public void whenFindBillingAddressByUsername_thenReturnPartyBillingAddressEntity() {
		 // when
    	Optional<PartyAddressEntity> found = partyAddressService.findByUsernameAndType("bob@bob", Constants.billingAddressCode);
     
        // then
    	assertFoundEntity(found);
	}
	
	@Test
	@Rollback(false)
	@WithUserDetails(value = "admin")
	public void whenFindByUsername_thenReturnPartyAddressDTO() {
		 // when
    	Optional<PartyAddressDTO> found = partyAddressService.findByUsernameAndRoleAndType("bob@bob", Constants.partyRoleCustomer, Constants.billingAddressCode);
     
        // then
    	assertFoundDTO(found);
	}
	
		 
    private void assertFoundEntity(final Optional<PartyAddressEntity> found) {
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	assertThat(found.get().getAddressLine1()).isEqualTo("Test address line 1");
    	
    	assertThat(found.get().getAddressLine2()).isEqualTo("Test address line 2");
    	
    	assertThat(found.get().getAddressLine3()).isEqualTo("Test address line 3");
    	
    	assertThat(found.get().getAddressCountry()).isEqualTo("Test Country");
    	
    	assertThat(found.get().getAddressPostCode()).isEqualTo("Test PC");
    	
    	assertThat(found.get().getType()).isNotNull();
    	
    	assertThat(found.get().getType().getAddressTypeCode()).isEqualTo("BIL01");
    	
    	assertThat(found.get().getParty()).isNotNull();
    	
    	assertThat(found.get().getParty().getUser().getUsername()).isEqualTo("bob@bob");
    }
    
    private void assertFoundDTO(Optional<PartyAddressDTO> found) {
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	assertThat(found.get().getAddressLine1()).isEqualTo("Test address line 1");
    	
    	assertThat(found.get().getAddressLine2()).isEqualTo("Test address line 2");
    	
    	assertThat(found.get().getAddressLine3()).isEqualTo("Test address line 3");
    	
    	assertThat(found.get().getCountry()).isEqualTo("Test Country");
    	
    	assertThat(found.get().getPostcode()).isEqualTo("Test PC");
    	
    	assertThat(found.get().getAddressType()).isNotNull();
    	
    	assertThat(found.get().getAddressType().getAddressTypeCode()).isEqualTo("BIL01");
    	
    	assertThat(found.get().getPerson()).isNotNull();
    	
    	assertThat(found.get().getPerson().getUserName()).isEqualTo("bob@bob");
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}
