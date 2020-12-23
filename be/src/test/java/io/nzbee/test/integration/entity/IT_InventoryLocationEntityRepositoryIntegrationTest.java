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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.inventory.location.IInventoryLocationService;
import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.test.integration.beans.entity.InventoryLocationEntityBeanFactory;

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
public class IT_InventoryLocationEntityRepositoryIntegrationTest {
 
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private InventoryLocationEntityBeanFactory inventoryLocationEntityBeanFactory;
 
    @Autowired
    private IInventoryLocationService inventoryLocationService;
    
	private InventoryLocation inventoryLocation = null;
	
	@MockBean
    private JavaMailSender mailSender;
    
    @Before
    public void setUp() { 
    	this.persistNewInventoryLocation();
    }
    
	public void persistNewInventoryLocation() {
		
		inventoryLocation = inventoryLocationEntityBeanFactory.getInventoryLocationEntityBean();
	    
	    entityManager.persist(inventoryLocation);
	    entityManager.flush();
	}
   
    
    @Test
    public void whenFindById_thenReturnInventoryLocation() {
    	
    	Optional<InventoryLocation> found = inventoryLocationService.findById(inventoryLocation.getLocationId());
     
        // then
    	assertFound(found);
    }
 

	@Test
    public void thenFindByCode_thenReturnInventoryLocation() {
    	
    	Optional<InventoryLocation> found = inventoryLocationService.findByCode("TST01");
    	
    	assertFound(found);
    	
    }
 
    
    private void assertFound(Optional<InventoryLocation> found) {
    	
    	assertNotNull(found);
    	assertTrue(found.isPresent());
    	
    	assertThat(found.get().getLocationCode())
	    .isEqualTo("TST01");
    	
    	assertThat(found.get().getLocationDesc())
	    .isEqualTo("test location");
    	
    	assertThat(found.get().getLocationIsActive()).isTrue();
    	
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
 
}