package io.nzbee.test.integration.entity.product.shipping;

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
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.util.product.shipping.ShippingProductMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class IT_ShippingProductUploadForCreateIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private ShippingProductMasterService pms;

	@Autowired
	private IProductService shippingProductService;

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
    	this.uploadShippingProducts();
        setUpIsDone = true;
	}
    
	public void uploadShippingProducts() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeShippingProductMaster(file.getAbsolutePath() + "/data/product/shipping/create/shipping_product_master.tsv");
	}

	@Test
	@Rollback(false)
	public void whenShippingProductUploadedForCreate_thenReturnCorrectlyCreatedShippingProduct_ENGB() {
		// when
		Optional<ProductEntity> found = shippingProductService.findByCode("AIR_PAR_1_AF_AF_0.001_1.000");

		// then
		assertFound_ENGB(found);
	}

	@Test
	@Rollback(false)
	public void whenShippingProductUploadedForCreate_thenReturnCorrectlyCreatedShippingProduct_ZHHK() {
		// when
		Optional<ProductEntity> found = shippingProductService.findByCode("AIR_PAR_1_AF_AF_0.001_1.000");

		// then
		assertFound_ZHHK(found);
	}

	
	private void assertFound_ENGB(Optional<ProductEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertNotNull(found.get().getAttributes());
		
		assertThat(found.get().getAttributes().stream().filter(f -> f.getLclCd().equals(Constants.localeENGB)).findAny().get().getProductDesc())
		.isEqualTo("Test localized shipping destination create");
		
	}

	private void assertFound_ZHHK(Optional<ProductEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getAttributes().stream().filter(f -> f.getLclCd().equals(Constants.localeZHHK)).findAny().get().getProductDesc())
		.isEqualTo("測試本地化的送貨目的地更新");
	}

}
