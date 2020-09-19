package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.test.integration.beans.BrandDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
public class IT_BrandDoServiceImplIntegrationTest {

	@TestConfiguration
    static class BrandServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
       
    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
    private IBrandPortService brandService;
	
	@Autowired
	private BrandDoBeanFactory brandDoBeanFactory;
	
	private Brand brand = null;
	
	
	public Brand persistNewBrand() {
		brand = brandDoBeanFactory.getBrandDoBean();
   	
	    brandService.save(brand);
	    	
	    return brand;
	}
	
    @Before
    public void setUp() { 
    	this.persistNewBrand();
    }
 
    @Test
    public void whenValidCode_thenBrandShouldBeFound() {
        Brand found = brandService.findByCode(Constants.localeENGB, "TST03");
      
        assertFound(found);
    }
    
    @Test
    public void whenValidDesc_thenBrandShouldBeFound() {
    	Brand found = brandService.findByDesc(Constants.localeENGB, "test brand");
      
        assertFound(found);
    }
    
    private void assertFound(final Brand found) {
    	
    	assertThat(found.getBrandCode())
        .isEqualTo("TST03");
	    
	    assertThat(found.getBrandDesc())
	    .isEqualTo("test brand");
    }
}