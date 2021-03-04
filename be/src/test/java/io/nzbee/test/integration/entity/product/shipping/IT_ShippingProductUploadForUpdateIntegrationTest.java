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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.entity.product.shipping.IShippingProductService;
import io.nzbee.entity.product.shipping.ShippingProductDTO;
import io.nzbee.util.product.shipping.ShippingProductMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_ShippingProductUploadForUpdateIntegrationTest {

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

		pms.writeShippingProductMaster(file.getAbsolutePath() + "/data/product/shipping/update/shipping_product_master.tsv");
	}

	@Test
	@Rollback(false)
	public void whenShippingProductUploadedForUpdate_thenReturnCorrectlyUpdatedShippingProduct_ENGB() {
		// when
		Optional<ProductDTO> found = shippingProductService.findByCode(Constants.localeENGB, "AIR_PAR_1_AF_AF_0.001_1.000");

		// then
		assertFound_ENGB(found);
	}

	@Test
	@Rollback(false)
	public void whenShippingProductUploadedForUpdate_thenReturnCorrectlyUpdatedShippingProduct_ZHHK() {
		// when
		Optional<ProductDTO> found = shippingProductService.findByCode(Constants.localeZHHK, "AIR_PAR_1_AF_AF_0.001_1.000");

		// then
		assertFound_ZHHK(found);
	}

	private void assertFound_ENGB(Optional<ProductDTO> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getProductDesc())
		.isEqualTo("Air Parcel to AFGHANISTAN update");
		
	}

	private void assertFound_ZHHK(Optional<ProductDTO> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getProductDesc())
		.isEqualTo("飛往阿富汗的航空包裹更新");
	}

}
