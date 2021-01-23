package io.nzbee.test.integration.entity.inventory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.inventory.location.IInventoryLocationService;
import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.util.inventory.InventoryLocationMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class IT_InventoryLocationUploadForUpdateIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private InventoryLocationMasterService pms;

	@Autowired
	private IInventoryLocationService inventoryLocationService;

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
		this.persistANewInventoryLocation();
		setUpIsDone = true;
	}
	
	public void persistANewInventoryLocation() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeInventoryLocation(file.getAbsolutePath() + "/data/inventory/location/update/inventory_location.tsv");
	}

	@Test
	@Rollback(false)
	public void whenInventoryLocationUploadedForCreate_thenReturnCorrectlyCreatedInventoryLocation_ENGB() {
		// when
		Optional<InventoryLocation> found = inventoryLocationService.findByCode("LCK01");

		// then
		assertFound(found);
	}

	private void assertFound(Optional<InventoryLocation> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getLocationCode())
		.isEqualTo("LCK01");
		
		assertThat(found.get().getLocationDesc())
		.isEqualTo("Test Update Location Description");
		
		assertThat(found.get().getLocationIsActive())
		.isEqualTo(false);
		
	}
}
