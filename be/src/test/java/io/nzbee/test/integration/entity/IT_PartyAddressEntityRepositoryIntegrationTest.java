package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.party.IPartyService;
import io.nzbee.entity.party.address.IPartyAddressService;
import io.nzbee.entity.party.address.PartyAddressDTO;
import io.nzbee.entity.party.address.PartyAddressEntity;
import io.nzbee.test.integration.beans.PartyAddressEntityBeanFactory;

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
    private IPartyService partyServie;
    
    @Autowired
    private PartyAddressEntityBeanFactory partyAddressEntityBeanFactory;
    
    private PartyAddressEntity p = null;
    
	public PartyAddressEntity persistNewPartyAddress() {
    	
		p = partyAddressEntityBeanFactory.getPartyAddressEntityBean(partyServie.findByCode("dmac088").get());
	    
	    entityManager.persist(p);
	    entityManager.flush();
	    	
	    return p;
	}
	
	@Before
	public void persistANewPartyAddress() {
		this.persistNewPartyAddress();
	}
	
	@Test
	public void whenFindById_thenReturnPartyAddress() {
		 // when
    	PartyAddressEntity found = partyAddressService.findById(p.getAddressId()).get();
     
        // then
    	assertFound(found);
	}
	
		 
    private void assertFound(final PartyAddressEntity found) {
    	assertNotNull(found);
    	
    	assertThat(found.getPartyAddressUPC())
        .isEqualTo("123456789");
    	
    	assertNotNull(found.getCategories());
    	
    	assertThat(found.getCategories().stream().filter(f -> f.getCategoryCode().equals("POM01")).findFirst().isPresent())
    	.isTrue();
    	
    	assertThat(found.getDepartment().getDepartmentCode())
    	.isEqualTo("ACC01");
    	
    	assertThat(found.getPartyAddressStatusCode())
    	.isEqualTo("ACT01");
    	
    	assertThat(found.getBrand().getBrandCode())
    	.isEqualTo("PLA01");
    	
    	assertThat(found.getPartyAddressDesc())
    	.isEqualTo("test product");
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}
