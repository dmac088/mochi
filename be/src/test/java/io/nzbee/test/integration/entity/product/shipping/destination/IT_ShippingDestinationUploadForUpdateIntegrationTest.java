package io.nzbee.test.integration.entity.product.shipping.destination;

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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.product.shipping.destination.IShippingDestinationService;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationDTO;
import io.nzbee.util.product.shipping.destination.ShippingDestinationMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_ShippingDestinationUploadForUpdateIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private ShippingDestinationMasterService pms;

	@Autowired
	private IShippingDestinationService shippingDestinationService;
	
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
    	this.uploadShippingDestinations();
        setUpIsDone = true;
	}

	public void uploadShippingDestinations() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeShippingDestinationMaster(file.getAbsolutePath() + "/data/product/shipping/destination/update/destination_master.tsv");
	}

	@Test
	@Rollback(false)
	public void whenShippingDestinationUploadedForUpdate_thenReturnCorrectlyUpdatedShippingDestination_ENGB() {
		// when
		Optional<ShippingDestinationDTO> found = shippingDestinationService.findByCode(Constants.localeENGB, "TST01");

		// then
		assertFound_ENGB(found);
	}

	@Test
	@Rollback(false)
	public void whenShippingDestinationUploadedForUpdate_thenReturnCorrectlyUpdatedShippingDestination_ZHHK() {
		// when
		Optional<ShippingDestinationDTO> found = shippingDestinationService.findByCode(Constants.localeZHHK, "TST01");

		// then
		assertFound_ZHHK(found);
	}

	private void assertFound_ENGB(Optional<ShippingDestinationDTO> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getShippingDestinationDesc())
		.isEqualTo("test shippingDestination");
		
	}

	private void assertFound_ZHHK(Optional<ShippingDestinationDTO> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getShippingDestinationDesc())
		.isEqualTo("測試標籤");
	}

}
