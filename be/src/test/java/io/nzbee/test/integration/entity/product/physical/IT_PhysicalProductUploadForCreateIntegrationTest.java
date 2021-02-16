package io.nzbee.test.integration.entity.product.physical;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.util.product.physical.PhysicalProductMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class IT_PhysicalProductUploadForCreateIntegrationTest {
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
    @Autowired
    private PhysicalProductMasterService pms;
    
    @Autowired
    private IProductService productService;
	
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
		this.loadProducts();
		setUpIsDone = true;
	}
	
	
	public void loadProducts() {
		String path = "src/test/resources";
		File file = new File(path);
	
		pms.writeProductMaster(file.getAbsolutePath() + "/data/product/physical/create/product_master.tsv");
	}
	
	@Test
	@Rollback(false)
	public void whenProductUploadedForCreation_thenReturnCreatedProduct_ENGB_USD() {
		 // when
    	Optional<ProductDTO> found = productService.findByCode( Constants.localeENGB, 
							  								  	Constants.currencyUSD,  
															  	"43254232");
     
        // then
    	assertFound_ENGB_USD(found);
	}
	
	@Test
	@Rollback(false)
	public void whenProductUploadedForCreation_thenReturnCreatedProduct_ZHHK_HKD() {
		 // when
    	Optional<ProductDTO> found = productService.findByCode( Constants.localeZHHK, 
							  								  	Constants.currencyHKD,  
															  	"43254232");
     
        // then
    	assertFound_ZHHK_HKD(found);
	}
	
	private void assertFound_ENGB_USD(Optional<ProductDTO> found) {
		assertNotNull(found);
		
		assertTrue(found.isPresent());

		assertThat(found.get().getProductUPC()).isEqualTo("43254232");

		assertThat(found.get().getProductDesc()).isEqualTo("organic cucumber");

		assertThat(found.get().getProductLongDesc()).isEqualTo("newly fresh organic cucumber");

		assertThat(found.get().getDepartment().getDepartmentCode()).isEqualTo("ACC01");

		assertThat(found.get().getProductStatusCode()).isEqualTo("ACT01");

		assertThat(found.get().getBrand().getBrandCode()).isEqualTo("DRI01");

		assertThat(found.get().getCurrency()).isEqualTo(Constants.currencyUSD);

		assertThat(found.get().getRetailPrice()).isEqualTo(new Double(8.0));

		assertThat(found.get().getMarkdownPrice()).isEqualTo(new Double(7.0));

		assertThat(found.get().getHeight()).isEqualTo(new Integer(0));
		
		assertThat(found.get().getWidth()).isEqualTo(new Integer(0));
		
		assertThat(found.get().getLength()).isEqualTo(new Integer(0));
		
		assertThat(found.get().getWeight()).isEqualTo(new Integer(0));
	}

	private void assertFound_ZHHK_HKD(Optional<ProductDTO> found) {
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getProductUPC()).isEqualTo("43254232");

		assertThat(found.get().getProductDesc()).isEqualTo("有機黃瓜");

		assertThat(found.get().getProductLongDesc()).isEqualTo("新近新鮮的有機黃瓜");

		assertThat(found.get().getDepartment().getDepartmentCode()).isEqualTo("ACC01");

		assertThat(found.get().getProductStatusCode()).isEqualTo("ACT01");

		assertThat(found.get().getBrand().getBrandCode()).isEqualTo("DRI01");

		assertThat(found.get().getCurrency()).isEqualTo(Constants.currencyHKD);

		assertThat(found.get().getRetailPrice()).isEqualTo(new Double(60.00));

		assertThat(found.get().getMarkdownPrice()).isEqualTo(new Double(55.00));
		
		assertThat(found.get().getHeight()).isEqualTo(new Integer(0));
		
		assertThat(found.get().getWidth()).isEqualTo(new Integer(0));
		
		assertThat(found.get().getLength()).isEqualTo(new Integer(0));
		
		assertThat(found.get().getWeight()).isEqualTo(new Integer(0));
	}
	
}
