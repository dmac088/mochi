package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.BrandServiceImpl;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.resources.brand.BrandResourceAssembler;
import io.nzbee.test.integration.beans.BrandEntityBeanFactory;
import io.nzbee.variables.GeneralVars;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
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
public class IT_BrandEntityRepositoryIntegrationTest {
 
	@TestConfiguration
    static class BrandEntityRepositoryIntegrationTest {
        
        @Bean(value = "brandEntityService")
        public IBrandService brandService() {
            return new BrandServiceImpl();
        }
        
        @Bean(value = "brandEntityBeanFactory")
        public BrandEntityBeanFactory brandFactoryBean() {
            return new BrandEntityBeanFactory();
        }
        
        @Bean(value = "bradResourceAssembler")
        public BrandResourceAssembler brandResourceAssembler() {
        	return new BrandResourceAssembler();
        }
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private BrandEntityBeanFactory brandEntityBeanFactory;
 
    @Autowired
    private IBrandService brandService;
    
	private Brand brand = null;
    
    @Before
    public void setUp() { 
    	this.persistNewBrand();
    }
    
	public void persistNewBrand() {
    	
		brand = brandEntityBeanFactory.getBrandEntityBean();
		
	    //persist a new transient test brand
	    entityManager.persist(brand);
	    entityManager.flush();
	    
	}
   
    
    @Test
    public void whenFindById_thenReturnBrand() {
    	
        // when
    	Brand found = brandService.findById(GeneralVars.LANGUAGE_ENGLISH, 
												  GeneralVars.CURRENCY_USD,  
												  brand.getId()).get();
     
        // then
    	assertFound(found);
    }
    
    // write test cases here
    @Test
    public void whenFindByCode_thenReturnBrand() {
    	
        // when
    	Brand found = brandService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
				 									GeneralVars.CURRENCY_USD, 
				 									"TST02").get();
     
        // then
    	assertFound(found);
    }
    
 // write test cases here
    @Test
    public void whenFindByDesc_thenReturnBrand() {
    	
        // when
    	Brand found = brandService.findByDesc(GeneralVars.LANGUAGE_ENGLISH, 
				 							  GeneralVars.CURRENCY_USD, 
				 							  "test brand").get();
     
        //then
    	assertFound(found);
    }
    
    private void assertFound(final Brand found) {
    	
    	assertThat(found.getBrandCode())
        .isEqualTo("TST02");
	    assertThat(found.getBrandAttribute().getBrandDesc())
	    .isEqualTo("test brand");
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
 
}