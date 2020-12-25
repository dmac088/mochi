package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import io.nzbee.Constants;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.test.integration.domain.beans.brand.IBrandDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
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
public class IT_BrandDoServiceImplIntegrationTest {
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
    private IBrandPortService brandService;
	
	@Autowired
	private IBrandDoBeanFactory brandDoBeanFactory;
	
	private Brand brand = null;
	
	public Brand persistNewBrand() {
		brand = brandDoBeanFactory.getBean();
   	
	    brandService.save(brand);
	    	
	    return brand;
	}
	
    @Before
    public void setUp() { 
    	this.persistNewBrand();
    }
 
    @Test
    public void whenValidCode_thenBrandShouldBeFound() {
        Brand found = brandService.findByCode(Constants.localeENGB, "GLO01");
      
        assertFound(found);
    }
    
    @Test
    public void whenValidDesc_thenBrandShouldBeFound() {
    	Brand found = brandService.findByDesc(Constants.localeENGB, "Glorys");
      
        assertFound(found);
    }
    
    private void assertFound(final Brand found) {
    	
    	assertNotNull(found);
    	
    	assertThat(found.getBrandCode())
        .isEqualTo("GLO01");
	    
	    assertThat(found.getBrandDesc())
	    .isEqualTo("Glorys");
    }
}
