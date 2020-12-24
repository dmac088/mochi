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
import io.nzbee.Constants;
import io.nzbee.entity.inventory.IInventoryTransactionService;
import io.nzbee.entity.inventory.InventoryTransaction;
import io.nzbee.test.integration.entity.beans.inventory.InventoryTransactionEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
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
public class IT_InventoryTransactionEntityRepositoryIntegrationTest {
 
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private InventoryTransactionEntityBeanFactory inventoryTransactionEntityBeanFactory;
 
	@Autowired
	private IInventoryTransactionService inventoryTransactionService;
   
	@MockBean
    private JavaMailSender mailSender;
	
	private InventoryTransaction inventoryTransaction = null;
	
    @Before
    public void setUp() { 
    	this.persistNewInventoryTransaction();
    }
    
	public void persistNewInventoryTransaction() {
	
		inventoryTransaction = inventoryTransactionEntityBeanFactory.getBean();
	    
	    entityManager.persist(inventoryTransaction);
	}
   
    
    @Test
    public void whenFindById_thenReturnInventoryTransaction() {
    	
    	Optional<InventoryTransaction> found = inventoryTransactionService.findById(inventoryTransaction.getInventroyTransactionId());
     
    	assertFound(found);
    }
    
    
    private void assertFound(Optional<InventoryTransaction> found) {
    	assertNotNull(found);
    	assertTrue(found.isPresent());
    	
    	assertThat(found.get().getInventoryLocation().getLocationCode())
	    .isEqualTo("LCK01");
    	
    	assertThat(found.get().getInventoryType().getInventoryTypeCode())
	    .isEqualTo("IN");
    	
    	assertThat(found.get().getCurrency().getCode())
	    .isEqualTo(Constants.currencyHKD);
    	
    	assertThat(found.get().getSupplier().getOrganizationName())
	    .isEqualTo("Taobao");
    	
    	assertThat(found.get().getQuantity())
	    .isEqualTo(new Long(5));
    	
    	assertThat(found.get().getPrice())
	    .isEqualTo(new Double(15.20));
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
 
}