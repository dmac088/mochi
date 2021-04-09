package io.nzbee.test.integration.entity.product.physical.light;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.physical.light.IPhysicalProductLightService;
import io.nzbee.entity.product.physical.light.PhysicalProductLightDTO;
import io.nzbee.test.integration.entity.beans.product.physical.IPhysicalProductEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_PhysicalProductLightEntityRepositoryIntegrationTest {

	@TestConfiguration
    static class ProductDTORepositoryIntegrationTest {
        
    }
	
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	private IPhysicalProductEntityBeanFactory productEntityBeanFactory;
 
    @Autowired
    private IProductService productEntityService;
    
    @Autowired
    private IPhysicalProductLightService physicalProductLightService;

    @Autowired
    @Qualifier("mochiDataSourceOwner")
    private DataSource database;
    
    private static ProductEntity product = null;

    private static boolean setUpIsDone = false;
    
    
	public ProductEntity persistNewProduct() {
    	
		product = productEntityBeanFactory.getBean();
	    
		productEntityService.save(product);
	    	
	    return product;
	}
	
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
    	this.persistNewProduct();
        setUpIsDone = true;
	}
	
	@Test
	@Rollback(false)
	public void whenFindByProductCodes_thenReturnPhysicalProductLightDTOs() {
		
		Set<String> codes = new HashSet<String>();
		codes.add(product.getProductUPC());
		
		 // when
    	List<PhysicalProductLightDTO> found = physicalProductLightService.findAll(Constants.localeENGB, 
									  								  	 		  Constants.currencyUSD,
									  								  	 		  Constants.primaryProductRootCategoryCode,
									  								  	 		  new StringCollectionWrapper(codes));
     
        // then
    	assertFound(found.get(0));
	}
	
	@Test
	@Rollback(false)
	public void whenFindByRootCategory_thenReturnPhysicalProductLightDTOs() {
		
		 // when
    	Page<PhysicalProductLightDTO> found = physicalProductLightService.findAll(Constants.localeENGB, 
									  								  	 		  Constants.currencyUSD,
									  								  	 		  "FRT01",
									  								  	 		  PageRequest.of(0, 10),
									  								  	 		  "");
     
        // then
    	assertNotNull(found);
    	assertThat(found.getTotalPages()).isEqualTo(3);
    	
	}
	
	@Test
	@Rollback(false)
    public void whenFindForFruitCategory_thenReturnAllFruitProducts() {
    	
		
        // when
    	Page<PhysicalProductLightDTO> found = physicalProductLightService.findAll(	 Constants.localeENGB, 
						    														 Constants.currencyUSD, 
						    														 "FRT01", 
						    														 new StringCollectionWrapper(new HashSet<String>()), 
						    														 new StringCollectionWrapper(new HashSet<String>()), 
						    														 new StringCollectionWrapper(new HashSet<String>()), 
						    														 new Double(10000), 
						    														 "0", 
						    														 "50", 
						    														 "priceAsc");
    
    	assertNotNull(found);
    	assertThat(found.getTotalElements()).isEqualTo(new Long(13));
    }
	
	
	@Test
	@Rollback(false)
    public void whenFindForFruitCategoryWithNullPrice_thenReturnAllFruitProducts() {
    	
        // when
    	Page<PhysicalProductLightDTO> found =		 physicalProductLightService.findAll( Constants.localeENGB, 
	    														 Constants.currencyUSD, 
	    														 "FRT01", 
	    														 new StringCollectionWrapper(new HashSet<String>()), 
	    														 new StringCollectionWrapper(new HashSet<String>()), 
	    														 new StringCollectionWrapper(new HashSet<String>()), 
	    														 null,
	    														 "0", 
	    														 "50", 
	    														 "priceAsc");
    
    	assertNotNull(found);
	}
	
	@Test
	@Rollback(false)
    public void whenFindForPomesCategory_thenReturnAllPomesProducts() {
    	
		Set<String> categories = new HashSet<String>();
		categories.add("POM01");
		
        // when
    	Page<PhysicalProductLightDTO> found =		 physicalProductLightService.findAll( Constants.localeENGB, 
	    														 Constants.currencyUSD, 
	    														 "FRT01", 
	    														 new StringCollectionWrapper(categories), 
	    														 new StringCollectionWrapper(new HashSet<String>()), 
	    														 new StringCollectionWrapper(new HashSet<String>()), 
	    														 new Double(10000),
	    														 "0", 
	    														 "50", 
	    														 "priceAsc");
    
    	assertNotNull(found);
    	assertThat(found.getTotalElements()).isEqualTo(new Long(4));
    }
	
	
	@Test
	@Rollback(false)
    public void whenFindForFruitWithOrganicTag_thenReturnAllOrganicFruitProducts() {
    	
		Set<String> tags = new HashSet<String>();
		tags.add("ORG01");
		
        // when
    	Page<PhysicalProductLightDTO> found =		 physicalProductLightService.findAll( Constants.localeENGB, 
	    														 Constants.currencyUSD, 
	    														 "FRT01", 
	    														 new StringCollectionWrapper(new HashSet<String>()), 
	    														 new StringCollectionWrapper(new HashSet<String>()), 
	    														 new StringCollectionWrapper(tags), 
	    														 new Double(10000),
	    														 "0", 
	    														 "50", 
	    														 "priceAsc");
    
        //then
    	assertNotNull(found);
    	assertThat(found.getTotalElements()).isEqualTo(new Long(2));
    }
	
	@Test
	@Rollback(false)
    public void whenFindForFruitWithBrandEnza_thenReturnAllEnzaFruitProducts() {
    	
		Set<String> brands = new HashSet<String>();
		brands.add("ENZ01");
		
        // when
    	Page<PhysicalProductLightDTO> found =		 physicalProductLightService.findAll( Constants.localeENGB, 
	    														 Constants.currencyUSD, 
	    														 "FRT01", 
	    														 new StringCollectionWrapper(new HashSet<String>()), 
	    														 new StringCollectionWrapper(brands), 
	    														 new StringCollectionWrapper(new HashSet<String>()),
	    														 new Double(10000),
	    														 "0", 
	    														 "50", 
	    														 "priceAsc");
    
        //then
    	assertNotNull(found);
    	assertThat(found.getTotalElements()).isEqualTo(new Long(1));
    }
	
	@Test
	@Rollback(false)
    public void whenFindForBrandEnza_thenReturnAllEnzaProducts() {
    	
		Set<String> brands = new HashSet<String>();
		brands.add("ENZ01");
		
        // when
    	Page<PhysicalProductLightDTO> found =		 physicalProductLightService.findAll( Constants.localeENGB, 
	    														 Constants.currencyUSD, 
	    														 "PRM01", 
	    														 new StringCollectionWrapper(new HashSet<String>()), 
	    														 new StringCollectionWrapper(brands), 
	    														 new StringCollectionWrapper(new HashSet<String>()),
	    														 new Double(10000),
	    														 "0", 
	    														 "50", 
	    														 "priceAsc");
    
        //then
    	assertNotNull(found);
    	assertThat(found.getTotalElements()).isEqualTo(new Long(4));
    }

    private void assertFound(PhysicalProductLightDTO physicalProductLightDTO) {
    	
    	assertNotNull(physicalProductLightDTO);
    
    	assertThat(physicalProductLightDTO.getProductupc())
        .isEqualTo("123456789");
    	
    	assertThat(physicalProductLightDTO.getBranddesc())
    	.isEqualTo("Planters");
    	
    	assertThat(physicalProductLightDTO.getRetailprice().doubleValue())
    	.isEqualTo(new Double(7.8));
    	
    	assertThat(physicalProductLightDTO.getInstock())
    	.isFalse();
    	
    	assertThat(physicalProductLightDTO.getMarkdownprice().doubleValue())
    	.isEqualTo(new Double(6.45));
    	
    }
    
}
