package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.util.HashSet;
import java.util.Set;
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
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.test.integration.beans.BrandEntityBeanFactory;

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
public class IT_BrandEntityRepositoryIntegrationTest {
 
	@TestConfiguration
    static class BrandEntityRepositoryIntegrationTest {
   
   
    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private BrandEntityBeanFactory brandEntityBeanFactory;
 
    @Autowired
    private IBrandService brandService;
    
	private BrandEntity brand = null;
    
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
    	BrandEntity found = brandService.findById(Constants.localeENGB, 
				  							brand.getBrandId()).get();
     
        // then
    	assertFound(found);
    }
    
    // write test cases here
    @Test
    public void whenFindByCode_thenReturnBrand() {
    	
        // when
    	BrandEntity found = brandService.findByCode("en-GB", 
											  "TST02").get();
     
        // then
    	assertFound(found);
    }
    
    // write test cases here
    @Test
    public void whenFindByDesc_thenReturnBrand() {
    	
        // when
    	BrandEntity found = brandService.findByDesc(Constants.localeENGB, 
				 							  "test brand").get();
     
        //then
    	assertFound(found);
    }
    
    @Test
    public void whenFindAllWithNoFacets_thenReturnCorrectResultCount() {
    	
    	Set<String> categoryCodes 	= new HashSet<String>();
    	Set<String> tagCodes 		= new HashSet<String>();
    	
        // when
    	Set<BrandEntity> lb = brandService.findAll(	Constants.localeENGB, 
				  								Constants.currencyUSD, 
				  								"FRT01", 
				  								categoryCodes, 
				  								tagCodes, 
				  								null);
     
        //then
    	assertNotNull(lb);
    	assertThat(lb.size()).isEqualTo(7);
    }
    
    @Test
    public void whenFindAllWithCategoryFacet_thenReturnCorrectResultCount() {
    	
    	Set<String> categoryCodes 	= new HashSet<String>();
    	Set<String> tagCodes 		= new HashSet<String>();
    	
    	categoryCodes.add("POM01");
    	
        // when
    	Set<BrandEntity> lb = brandService.findAll(	Constants.localeENGB, 
				  								Constants.currencyUSD, 
				  								"FRT01", 
				  								categoryCodes, 
				  								tagCodes, 
				  								null);
     
        //then
    	assertNotNull(lb);
    	assertThat(lb.size()).isEqualTo(3);
    }
    
    @Test
	public void whenFindAllWithPriceFacetHKD_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> tagCodes = new HashSet<String>();

		Double price = new Double("32.4");

		// when
    	Set<BrandEntity> lb = brandService.findAll(	Constants.localeENGB, 
				  								Constants.currencyHKD, 
				  								"FRT01", 
				  								categoryCodes, 
				  								tagCodes, 
				  								price
				  								);
     
        //then
    	assertNotNull(lb);
    	assertThat(lb.size()).isEqualTo(3);
	}
    
    @Test
	public void whenFindAllWithPriceFacetUSD_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> tagCodes = new HashSet<String>();

		Double price = new Double("4.15");

		// when
    	Set<BrandEntity> lb = brandService.findAll(	Constants.localeENGB, 
				  								Constants.currencyUSD, 
				  								"FRT01", 
				  								categoryCodes, 
				  								tagCodes, 
				  								price
				  								);
     
        //then
    	assertNotNull(lb);
    	assertThat(lb.size()).isEqualTo(2);

	}
    
    private void assertFound(final BrandEntity found) {
    	
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