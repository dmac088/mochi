package io.nzbee.test.integration.entity.product.shipping.type;

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
import io.nzbee.Constants;
import io.nzbee.entity.product.shipping.type.IShippingTypeService;
import io.nzbee.entity.product.shipping.type.ShippingTypeEntity;
import io.nzbee.util.product.shipping.type.ShippingTypeMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class IT_ShippingTypeUploadForCreateIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private ShippingTypeMasterService pms;

	@Autowired
	private IShippingTypeService shippingTypeService;

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
    	this.uploadShippingTypes();
        setUpIsDone = true;
	}
    
	public void uploadShippingTypes() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeShippingTypeMaster(file.getAbsolutePath() + "/data/product/shipping/type/create/type_master.tsv");
	}

	@Test
	@Rollback(false)
	public void whenShippingTypeUploadedForCreate_thenReturnCorrectlyCreatedShippingType_ENGB() {
		// when
		Optional<ShippingTypeEntity> found = shippingTypeService.findByCode("TST_000_1");

		// then
		assertFound_ENGB(found);
	}

	@Test
	@Rollback(false)
	public void whenShippingTypeUploadedForCreate_thenReturnCorrectlyCreatedShippingType_ZHHK() {
		// when
		Optional<ShippingTypeEntity> found = shippingTypeService.findByCode("TST_000_1");

		// then
		assertFound_ZHHK(found);
	}

	
	private void assertFound_ENGB(Optional<ShippingTypeEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getAttributes().stream().filter(f -> f.getLclCd().equals(Constants.localeENGB)).findAny().get().getShippingTypeDesc())
		.isEqualTo("Test localized shipping type create");
		
	}

	private void assertFound_ZHHK(Optional<ShippingTypeEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getAttributes().stream().filter(f -> f.getLclCd().equals(Constants.localeZHHK)).findAny().get().getShippingTypeDesc())
		.isEqualTo("測試本地化的送貨目的地更新");
	}

}
