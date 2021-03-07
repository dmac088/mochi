package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
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
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.test.integration.domain.beans.brand.BrandDoBeanFactory;
import io.nzbee.test.integration.domain.beans.brand.IBrandDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
public class IT_BrandDoServiceImplIntegrationTest {
	
	@TestConfiguration
	static class BrandDoServiceImplIntegrationTest_Configuration {
		// the beans that we need to run this test
		@Bean
		public IBrandDoBeanFactory categoryDoBeanFactory() {
			return new BrandDoBeanFactory();
		}
		
	}
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
    private IBrandPortService brandService;
	
	@Autowired
	private IBrandDoBeanFactory brandDoBeanFactory;
	
	private static Brand brand = null;

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
		this.persistNewBrand();
		setUpIsDone = true;
	}
	
	public Brand persistNewBrand() {
		brand = brandDoBeanFactory.getBean();
   	
	    brandService.save(brand);
	    	
	    return brand;
	}

 
    @Test
	@Rollback(false)
    public void whenValidCode_thenBrandShouldBeFound() {
        Brand found = brandService.findByCode(Constants.localeENGB, "GLO01");
      
        assertFound(found);
    }
    
    @Test
	@Rollback(false)
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
