package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.brand.BrandServiceImpl;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.test.integration.beans.BrandEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
public class IT_BrandDoServiceImplIntegrationTest {

	@TestConfiguration
    static class BrandServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
        @Bean(value = "brandDomainService")
        public IBrandService brandService() {
            return new BrandServiceImpl();
        }
        
        @Bean(value = "brandEntityService")
        public io.nzbee.entity.brand.IBrandService brandEntityService() {
        	return new io.nzbee.entity.brand.BrandServiceImpl();
        }
      
        @Bean(value = "brandEntityBeanFactory")
        public BrandEntityBeanFactory brandFactoryBean() {
            return new BrandEntityBeanFactory();
        }
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
    private IBrandService brandService;
	
	@Autowired
	private BrandEntityBeanFactory brandEntityBeanFactory;
	
	private io.nzbee.entity.brand.Brand brand = null;
	
	
	public io.nzbee.entity.brand.Brand persistNewBrand() {
		brand = brandEntityBeanFactory.getBrandEntityBean();
   	
	    //persist a new transient test category
	    entityManager.persist(brand);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return brand;
	}
	
    @Before
    public void setUp() { 
    	this.persistNewBrand();
    }
 
    @Test
    public void whenValidCode_thenBrandShouldBeFound() {
        String code = "TST02";
        
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
        .isEqualTo("TST02");
	    
	    assertThat(found.getBrandDesc())
	    .isEqualTo("test brand");
    }
}
