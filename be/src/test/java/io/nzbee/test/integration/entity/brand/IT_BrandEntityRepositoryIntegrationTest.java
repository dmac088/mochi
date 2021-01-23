package io.nzbee.test.integration.entity.brand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.brand.BrandDTO;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.test.integration.entity.beans.brand.IBrandEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_BrandEntityRepositoryIntegrationTest {
 
	@TestConfiguration
    static class BrandEntityRepositoryIntegrationTest {

    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	private IBrandEntityBeanFactory brandEntityBeanFactory;
 
    @Autowired
    private IBrandService brandService;
   
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
    
	private static BrandEntity brand = null;
	
	private static boolean setUpIsDone = false;
      
	@Before
	public void persistANewBrand() {
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

	public void persistNewBrand() {
    	
		brand = brandEntityBeanFactory.getBean();
		
	    //persist a new transient test brand
		brandService.save(brand);
	}
    
    @Test
    @Rollback(false)
    public void whenFindById_thenReturnBrandEntity() {
    	
        // when
    	Optional<BrandEntity> found = brandService.findById(brand.getBrandId());
     
        // then
    	assertFoundEntity(found);
    }
    
    
    @Test
    @Rollback(false)
    public void whenFindByCode_thenReturnBrandEntity() {
    	
        // when
    	Optional<BrandEntity> found = brandService.findByCode("TST02");
     
        // then
    	assertFoundEntity(found);
    }
    
	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnBrandDTO() {

		// when
		Optional<BrandDTO> found = brandService.findByCode(Constants.localeENGB, "DRI01");

		// then
		assertFoundDTO(found);
	}
	
	@Test
	@Rollback(false)
	public void whenFindByDesc_thenReturnBrandDTO() {

		// when
		Optional<BrandDTO> found = brandService.findByDesc(Constants.localeENGB, "Driscolls");

		// then
		assertFoundDTO(found);
	}
   
    
    @Test
    @Rollback(false)
    public void whenFindAllWithNoFacets_thenReturnCorrectResultCount() {
    	
    	Set<String> categoryCodes 	= new HashSet<String>();
    	Set<String> tagCodes 		= new HashSet<String>();
    	
        // when
    	List<BrandDTO> lb = brandService.findAll(	Constants.localeENGB, 
					  								Constants.currencyUSD, 
					  								"FRT01", 
					  								new StringCollectionWrapper(categoryCodes), 
					  								new StringCollectionWrapper(tagCodes), 
					  								null);
     
        //then
    	assertNotNull(lb);
    	assertThat(lb.size()).isEqualTo(7);
    }
    
    @Test
    @Rollback(false)
    public void whenFindAllWithCategoryFacet_thenReturnCorrectResultCount() {
    	
    	Set<String> categoryCodes 	= new HashSet<String>();
    	Set<String> tagCodes 		= new HashSet<String>();
    	
    	categoryCodes.add("POM01");
    	
        // when
    	List<BrandDTO> lb = brandService.findAll(	Constants.localeENGB, 
					  								Constants.currencyUSD, 
					  								"FRT01", 
					  								new StringCollectionWrapper(categoryCodes), 
					  								new StringCollectionWrapper(tagCodes), 
					  								null);
     
        //then
    	assertNotNull(lb);
    	assertThat(lb.size()).isEqualTo(3);
    }
    
    @Test
    @Rollback(false)
	public void whenFindAllWithPriceFacetHKD_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> tagCodes = new HashSet<String>();

		Double price = new Double("32.4");

		// when
		List<BrandDTO> lb = brandService.findAll(	Constants.localeENGB, 
					  								Constants.currencyHKD, 
					  								"FRT01", 
					  								new StringCollectionWrapper(categoryCodes), 
					  								new StringCollectionWrapper(tagCodes), 
					  								price
					  								);
     
        //then
    	assertNotNull(lb);
    	assertThat(lb.size()).isEqualTo(3);
	}
    
    @Test
    @Rollback(false)
	public void whenFindAllWithPriceFacetUSD_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> tagCodes = new HashSet<String>();

		Double price = new Double("4.15");

		// when
		List<BrandDTO> lb = brandService.findAll(	Constants.localeENGB, 
					  								Constants.currencyUSD, 
					  								"FRT01", 
					  								new StringCollectionWrapper(categoryCodes), 
					  								new StringCollectionWrapper(tagCodes), 
					  								price);
     
        //then
    	assertNotNull(lb);
    	assertThat(lb.size()).isEqualTo(2);

	}
    
    private void assertFoundEntity(Optional<BrandEntity> found) {
    	
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	assertThat(found.get().getBrandCode())
        .isEqualTo("TST02");
    	
	    assertThat(found.get().getBrandDescENGB())
	    .isEqualTo("test brand");
	    
	    assertThat(found.get().getBrandDescZHHK())
	    .isEqualTo("測試品牌");
	    
    }
    
   private void assertFoundDTO(Optional<BrandDTO> found) {
    	
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	assertThat(found.get().getBrandCode())
        .isEqualTo("DRI01");
    	
	    assertThat(found.get().getBrandDesc())
	    .isEqualTo("Driscolls");
	    
    }
 
}