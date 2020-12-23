package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Optional;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;

import io.nzbee.Constants;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.address.IPartyAddressService;
import io.nzbee.entity.party.address.PartyAddressEntity;
import io.nzbee.entity.party.address.type.AddressTypeEntity;
import io.nzbee.entity.party.address.type.IAddressTypeService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.test.integration.beans.entity.PartyAddressEntityBeanFactory;

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
			transactionMode = TransactionMode.ISOLATED))
})
public class IT_PartyAddressEntityRepositoryIntegrationTest {

	@TestConfiguration
    static class PartyAddressEntityRepositoryIntegrationTest {
        
    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
 
    @Autowired
    private IPartyAddressService partyAddressService;
    
    @Autowired 
    private IPersonService personService;
    
    @Autowired
    private PartyAddressEntityBeanFactory partyAddressEntityBeanFactory;
    
    @Autowired 
    private IAddressTypeService addressTypeService;
    
    private PartyAddressEntity p = null;
    
	public PartyAddressEntity persistNewPartyAddress() {
    	
		AddressTypeEntity ate = addressTypeService.findByCode("BIL01").get();
		Party party = personService.findByUsernameAndRole("bob@bob", Constants.partyRoleCustomer).get();
		
		p = partyAddressEntityBeanFactory.getPartyAddressEntityBean(party, ate);
	    
	    entityManager.persist(p);
	    	
	    return p;
	}
	
	@Before
	public void persistANewPartyAddress() {
		this.persistNewPartyAddress();
	}
	
	@Test
	@Rollback(false)
	@WithUserDetails(value = "admin")
	public void whenFindById_thenReturnPartyAddress() {
		 // when
    	Optional<PartyAddressEntity> found = partyAddressService.findById(p.getAddressId());
     
        // then
    	assertFound(found);
	}
	
		 
    private void assertFound(final Optional<PartyAddressEntity> found) {
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
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}
