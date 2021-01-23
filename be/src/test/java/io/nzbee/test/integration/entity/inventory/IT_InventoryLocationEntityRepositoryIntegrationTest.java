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
import io.nzbee.entity.inventory.location.IInventoryLocationService;
import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.test.integration.entity.beans.inventory.location.IInventoryLocationEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_InventoryLocationEntityRepositoryIntegrationTest {
	
	@Autowired
	private IInventoryLocationEntityBeanFactory inventoryLocationEntityBeanFactory;
 
    @Autowired
    private IInventoryLocationService inventoryLocationService;
    
	private InventoryLocation inventoryLocation = null;
	
	@MockBean
    private JavaMailSender mailSender;
    
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
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
		this.persistNewInventoryLocation();
		setUpIsDone = true;
	}
	
    
	public void persistNewInventoryLocation() {
		
		inventoryLocation = inventoryLocationEntityBeanFactory.getBean();
	    
		inventoryLocationService.save(inventoryLocation);
	}
   
    
    @Test
    @Rollback(false)
    public void whenFindById_thenReturnInventoryLocation() {
    	
    	Optional<InventoryLocation> found = inventoryLocationService.findById(inventoryLocation.getLocationId());
     
        // then
    	assertFound(found);
    }
 

	@Test
	@Rollback(false)
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
 
}