package io.nzbee.test.integration.entity;

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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.bag.BagDTO;
import io.nzbee.entity.bag.BagEntity;
import io.nzbee.entity.bag.IBagService;
import io.nzbee.entity.party.person.IPersonService;
import io.nzbee.entity.party.person.PersonEntity;
import io.nzbee.test.integration.beans.BagEntityBeanFactory;

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
public class IT_BagEntityRepositoryIntegrationTest {
 
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private BagEntityBeanFactory bagEntityBeanFactory;
 
    @Autowired
    private IBagService bagService;
    
	@Autowired
    private IPersonService personService;
 
	private BagEntity bag = null;
	
	@MockBean
    private JavaMailSender mailSender;
    
    @Before
    public void setUp() { 
    	this.persistNewBagEntity();
    }
    
	public BagEntity persistNewBagEntity() {
		
		Optional<PersonEntity> p = personService.findByUsernameAndRole("dmac088", Constants.partyRoleCustomer);
		
		System.out.println(p.isPresent());
		
		bag = bagEntityBeanFactory.getBagEntityBean(p.get());
	    
	    //persist a new transient test category
	    entityManager.persist(bag);
	    
	    return bag;
	    
	}
   
    
    @Test
	@WithUserDetails(value = "admin")
    public void whenFindById_thenReturnBagEntity() {
    	
    	//persist a bag and then make sure we can retrieve it by id
    	Optional<BagEntity> found = bagService.findById(bag.getBagId());
     
        // then
    	assertEntityFound(found);
    }
    
    @Test
	@WithUserDetails(value = "admin")
    public void whenFindByUsername_thenReturnBagEntity() {
    	
    	//persist a bag and then make sure we can retrieve it by username which is the natural key of the bag
    	Optional<BagEntity> found = bagService.findByCode("dmac088");
    	
    	//then
    	assertEntityFound(found);
    }
    
    @Test
	@WithUserDetails(value = "admin")
    public void whenFindByUsername_thenReturnBagDTO() {
    	
    	//persist a bag and then make sure we can retrieve it by username which is the natural key of the bag
    	Optional<BagDTO> found = bagService.findByCode(Constants.localeENGB, Constants.currencyHKD, "dmac088");
    	
    	//then
    	assertDTOFound(found);
    }
 
    
    private void assertEntityFound(Optional<BagEntity> bag) {
    	assertNotNull(bag);
    	
    	assertTrue(bag.isPresent());
    	
    	assertNotNull(bag.get().getBagCreatedDateTime());
    	assertNotNull(bag.get().getBagUpdatedDateTime());
    }
    
    private void assertDTOFound(Optional<BagDTO> bag) {
    	assertNotNull(bag);
    	
    	assertTrue(bag.isPresent());
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
 
}