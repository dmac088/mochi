package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.io.File;
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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.Product;
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
public class IT_ProductUploadForUpdateIntegrationTest {

	@TestConfiguration
    static class ProductUploadRepositoryIntegrationTest {
        
    }
	
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
	
		pms.writeProductMaster(file.getAbsolutePath() + "/data/product/update/product_master.tsv");
	}
	
	@Test
	public void whenProductUploadedForUpdate_thenReturnCorrectlyUpdatedProduct() {
		 // when
    	Product found = productService.findByCode(  Constants.localeENGB, 
				  								  Constants.currencyUSD,  
												  "12383658").get();
     
        // then
    	assertFound(found);
	}
	
private void assertFound(final Product found) {
    	
    	assertThat(found.getUPC())
        .isEqualTo("12383658");
    	
    	assertThat(found.getProductAttribute().getProductDesc())
        .isEqualTo("organic cucumber");
    	
    	assertThat(found.getProductAttribute().getProductLongDesc())
        .isEqualTo("newly fresh organic cucumber");
    	
    	assertThat(found.getDepartment().getDepartmentCode())
    	.isEqualTo("ACC01");
    	
    	assertThat(found.getProductStatus().getCode())
    	.isEqualTo("ACT01");
    	
    	assertThat(found.getBrand().getBrandCode())
    	.isEqualTo("DRI01");
    	
    	assertNotNull(found.getPrimaryCategory());
    	assertThat(found.getPrimaryCategory().getCategoryCode().equals("SVG01")).isTrue();
    	
    	assertThat(found.getCurrentRetailPriceHKD())
    	.isEqualTo(new Double(60));
    	
    	assertThat(found.getCurrentRetailPriceUSD())
    	.isEqualTo(new Double(8));
    	
    	assertThat(found.getCurrentMarkdownPriceHKD())
    	.isEqualTo(new Double(55));
    	
    	assertThat(found.getCurrentMarkdownPriceUSD())
    	.isEqualTo(new Double(7));
    	
    	assertNotNull(found.getTags());
    	assertThat(found.getTags().stream().filter(f -> f.getTagCode().equals("ORG01")).findFirst().isPresent()).isTrue();
    }
	
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}
