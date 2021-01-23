package io.nzbee.test.integration.entity.inventory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.inventory.IInventoryTransactionService;
import io.nzbee.entity.inventory.InventoryTransaction;
import io.nzbee.entity.stock.IStockOnHandService;
import io.nzbee.entity.stock.StockOnHand;
import io.nzbee.util.inventory.InventoryMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_InventoryTransactionUploadIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private InventoryMasterService pms;

	@Autowired
	private IInventoryTransactionService inventoryService;
	
	@Autowired
	private IStockOnHandService sohService;
	
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
		this.persistNewInventoryTransaction();
		setUpIsDone = true;
	}

	public void persistNewInventoryTransaction() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeInventoryTransaction(file.getAbsolutePath() + "/data/inventory/inventory.tsv");
	}

	@Test
	@Rollback(false)
	public void whenInventoryTransactionUploaded_thenReturnCorrectInventoryTransactionCount() {
		
		// when
		List<InventoryTransaction> found = inventoryService.findByProductCode("3577789");

		//then
		assertFound(found);
	}
	
	@Test
	@Rollback(false)
	public void whenInventoryTransactionUploaded_thenReturnCorrectStockOnHand() {
		
		// when
		Optional<StockOnHand> found = sohService.findByProductCode("3577789");

		//then
		assertFound(found);
	}	
	
	private void assertFound(List<InventoryTransaction> found) {
		
		assertNotNull(found);
		
		assertThat(found.size()).isEqualTo(2);
		
	}
	
	private void assertFound(Optional<StockOnHand> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getStockOnHand()).isEqualTo(25);
		
	}

}
