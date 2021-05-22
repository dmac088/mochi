package io.nzbee.test.integration.entity.inventory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.inventory.IInventoryTransactionService;
import io.nzbee.entity.inventory.InventoryTransaction;
import io.nzbee.test.integration.entity.beans.inventory.InventoryTransactionEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_InventoryTransactionEntityRepositoryIntegrationTest {
	
	@Autowired
	private InventoryTransactionEntityBeanFactory inventoryTransactionEntityBeanFactory;
 
	@Autowired
	private IInventoryTransactionService inventoryTransactionService;
   
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
    
	private static InventoryTransaction inventoryTransaction = null;
	
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
		this.persistNewInventoryTransaction();
		setUpIsDone = true;
	}

    
	public void persistNewInventoryTransaction() {
	
		inventoryTransaction = inventoryTransactionEntityBeanFactory.getBean();
	    
		inventoryTransactionService.save(inventoryTransaction);
	}
   
    
    @Test
    @Rollback(false)
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
	    .isEqualTo(Long.valueOf(5));
    	
    	assertThat(found.get().getPrice())
	    .isEqualTo(Double.valueOf(15.20));
    }
    
 
}