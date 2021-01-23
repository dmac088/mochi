package io.nzbee.test.integration.entity.inventory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.util.List;
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
import io.nzbee.entity.inventory.IInventoryTransactionService;
import io.nzbee.entity.inventory.InventoryTransaction;
import io.nzbee.entity.stock.IStockOnHandService;
import io.nzbee.entity.stock.StockOnHand;
import io.nzbee.util.inventory.InventoryMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@SqlGroup({
		@Sql(scripts = "/database/mochi_schema.sql", config = @SqlConfig(dataSource = "mochiDataSourceOwner", transactionManager = "mochiTransactionManagerOwner", transactionMode = TransactionMode.ISOLATED)),
		@Sql(scripts = "/database/mochi_data.sql", config = @SqlConfig(dataSource = "mochiDataSource", transactionManager = "mochiTransactionManager", transactionMode = TransactionMode.ISOLATED)) })
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

	@Before
	public void persistNewInventoryTransaction() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeInventoryTransaction(file.getAbsolutePath() + "/data/inventory/inventory.tsv");
	}

	@Test
	public void whenInventoryTransactionUploaded_thenReturnCorrectInventoryTransactionCount() {
		
		// when
		List<InventoryTransaction> found = inventoryService.findByProductCode("3577789");

		//then
		assertFound(found);
	}
	
	@Test
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
	
	@After
	public void closeConnection() {
		entityManager.close();
	}
}
