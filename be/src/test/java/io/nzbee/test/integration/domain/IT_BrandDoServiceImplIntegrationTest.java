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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.test.integration.beans.BrandDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
public class IT_BrandDoServiceImplIntegrationTest {

	@TestConfiguration
    static class BrandServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
       
    }
	
	@Autowired
    private IBrandService brandService;
	
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
        String code = "TST03";
        
        Brand found = brandService.findByCode("en-GB", "HKD", code);
      
        assertFound(found);
    }
    
    @Test
    public void whenValidDesc_thenBrandShouldBeFound() {
    	String desc = "test brand";
    	
    	Brand found = brandService.findByDesc("en-GB", "HKD", desc);
      
        assertFound(found);
    }
    
    private void assertFound(final Brand found) {

    	assertThat(found.getBrandCode())
        .isEqualTo("TST03");
	    
	    assertThat(found.getBrandDesc())
	    .isEqualTo("test brand");
    }
}
