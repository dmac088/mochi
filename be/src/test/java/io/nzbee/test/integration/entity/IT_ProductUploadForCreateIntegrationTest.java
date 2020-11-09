package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.util.product.ProductMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
@SqlGroup({
	@Sql(scripts = "/database/mochi_schema.sql",
			config = @SqlConfig(dataSource = "mochiDataSourceOwner", 
			transactionManager = "mochiTransactionManagerOwner",
			transactionMode = TransactionMode.ISOLATED)), 
	@Sql(scripts = "/database/mochi_data.sql",
			config = @SqlConfig(dataSource = "mochiDataSource", 
			transactionManager = "mochiTransactionManager",
			transactionMode = TransactionMode.ISOLATED))
})
public class IT_ProductUploadForCreateIntegrationTest {
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
    @Autowired
    private ProductMasterService pms;
    
    @Autowired
    private IProductService productService;
	
	@Before
	public void persistANewProduct() {
		String path = "src/test/resources";
		File file = new File(path);
	
		pms.writeProductMaster(file.getAbsolutePath() + "/data/product/create/product_master.tsv");
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

		//assertThat(found.get().getProductLongDesc()).isEqualTo("newly fresh organic cucumber");

		assertThat(found.get().getDepartment().getDepartmentCode()).isEqualTo("ACC01");

		assertThat(found.get().getProductStatusCode()).isEqualTo("ACT01");

		assertThat(found.get().getBrand().getBrandCode()).isEqualTo("DRI01");

		assertThat(found.get().getCurrency()).isEqualTo(Constants.currencyUSD);

		assertThat(found.get().getRetailPrice()).isEqualTo(new Double(8.0));

		assertThat(found.get().getMarkdownPrice()).isEqualTo(new Double(7.0));

//		assertNotNull(found.get().getTags());
//		assertThat(found.get().getTags().stream().filter(f -> f.getTagCode().equals("ORG01")).findFirst().isPresent())
//				.isTrue();
	}

	private void assertFound_ZHHK_HKD(Optional<ProductDTO> found) {
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getProductUPC()).isEqualTo("43254232");

		assertThat(found.get().getProductDesc()).isEqualTo("有機黃瓜");

		//assertThat(found.get().getProductLongDesc()).isEqualTo("新近新鮮的有機黃瓜");

		assertThat(found.get().getDepartment().getDepartmentCode()).isEqualTo("ACC01");

		assertThat(found.get().getProductStatusCode()).isEqualTo("ACT01");

		assertThat(found.get().getBrand().getBrandCode()).isEqualTo("DRI01");

		assertThat(found.get().getCurrency()).isEqualTo(Constants.currencyHKD);

		assertThat(found.get().getRetailPrice()).isEqualTo(new Double(60.00));

		assertThat(found.get().getMarkdownPrice()).isEqualTo(new Double(55.00));

//		assertNotNull(found.get().getTags());
//		assertThat(found.get().getTags().stream().filter(f -> f.getTagCode().equals("ORG01")).findFirst().isPresent())
//				.isTrue();
	}
	
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}
