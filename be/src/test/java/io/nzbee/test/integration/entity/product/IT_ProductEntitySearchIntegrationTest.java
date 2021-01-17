package io.nzbee.test.integration.entity.product;

import static org.assertj.core.api.Assertions.assertThat;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.hibernate.CacheMode;
import org.hibernate.search.batchindexing.impl.SimpleIndexingProgressMonitor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
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
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.search.facet.IFacet;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_ProductEntitySearchIntegrationTest {

	@TestConfiguration
    static class ProductEntityRepositoryIntegrationTest {
     
    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
    
	@Autowired
    private IProductPortService productService;
	
    @Autowired
    @Qualifier("mochiDataSourceOwner")
    private DataSource database;
   
	private Set<IFacet> facetPayload = new HashSet<IFacet>();
	private Set<IFacet> returnFacets = new HashSet<IFacet>();

    private static boolean setUpIsDone = false;
	
	@Before
	public void setUp() {
		if (setUpIsDone) {
            return;
        }
		
		FullTextEntityManager fullTextEntityManager 
		  = Search.getFullTextEntityManager(entityManager);
		
    	try (Connection con = database.getConnection()) {
            ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_schema.sql"));
            ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_data.sql"));
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	entityManager.close();
		
    	try {
			fullTextEntityManager
			.createIndexer( ProductEntity.class )
			.batchSizeToLoadObjects( 25 )
			.cacheMode( CacheMode.NORMAL )
			.threadsToLoadObjects( 12 )
			.idFetchSize( 150 )
			.transactionTimeout( 1800 )
			.progressMonitor( new SimpleIndexingProgressMonitor() ) //a MassIndexerProgressMonitor implementation
			.startAndWait();
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setUpIsDone = true;
	}
	
	
	@Test
	@Rollback(false)
	public void whenSearchFruit_thenReturnFruitProducts() {
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD",
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "Fruit",
							  facetPayload,
							  returnFacets);
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(2);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(12));
	}
	
	@Test
	@Rollback(false)
	public void whenSearchFruit_thenReturnFruitProductsZHHK() {
		// when
		Page<Product> 
						pp = productService.search(
							  "zh-HK", 
							  "HKD",
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "水果", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(2);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(12));
	}
	

	@Test
	@Rollback(false)
	public void whenSearchVegetables_thenReturnVegetableProducts() {
		
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD",
							  "Ignored",
							  0,
							  10, 
							  "nameAsc",
							  "Vegetables",
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(2);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(12));
	}
	
	@Test
	@Rollback(false)
	public void whenSearchApple_thenReturnAppleProduct() {
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD", 
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "Apple",	
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(1));
	} 
	
	@Test
	@Rollback(false)
	public void whenSearchCauliflower_thenReturnCauliflowerProduct() {
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD", 
							  "Ignored",
							  0,
							  10, 
							  "nameAsc",
							  "Cauliflower", 					
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(1));
	} 
	
	@Test
	@Rollback(false)
	public void whenSearchAll_thenReturnAllProducts() {
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD", 
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "All", 						
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(3);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(29));
	} 
	
	@Test
	@Rollback(false)
	public void whenSearchNuts_thenReturnNuts() {
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD", 
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "Nuts", 					
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(5));
	} 
	
	@Test
	@Rollback(false)
	public void whenSearchOrganic_thenReturnOrganicProducts() {
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD", 
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "Organic", 					
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(1));
	} 
	
	@Test
	@Rollback(false)
	public void whenSearchOrganic_thenReturnOrganicProductsZHHK() {
		// when
		Page<Product> 
						pp = productService.search(
							  "zh-HK", 
							  "HKD", 
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "有機", 						
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(1));
	} 
	
	@Test
	@Rollback(false)
	public void whenSearchOrganic_thenReturnOrganicGlutenFreeZHHK() {
		// when
		Page<Product> 
						pp = productService.search(
							  "zh-HK", 
							  "HKD", 
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "不含麩質", 						
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(5));
	} 
	
	@Test
	@Rollback(false)
	public void whenSearchGlutenFree_thenReturnGlutenFreeProducts() {
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD", 
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "Gluten Free", 				
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(5));
	}
	
	@Test
	@Rollback(false)
	public void whenSearchGlorys_thenReturnGlorysProducts() {
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD", 
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "Glorys", 				
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(4));
	}
	
	@Test
	@Rollback(false)
	public void whenSearchEnza_thenReturnEnzaProducts() {
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD", 
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "Enza", 					
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(4));
	}
	
	@Test
	@Rollback(false)
	public void whenSearchPlanters_thenReturnPlantersProducts() {
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD", 
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "Planters", 						
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(4));
	}
	
	@Test
	@Rollback(false)
	public void whenSearchPlanters_thenReturnDoleProducts() {
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD", 
							  "Ignored",
							  0,
							  10, 
							  "nameAsc",
							  "Dole", 						
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(2));
	}
	
	@Test
	@Rollback(false)
	public void whenSearchDriscolls_thenReturnDriscollsProducts() {
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD", 
							  "Ignored",
							  0,
							  10,
							  "nameAsc",							 							
							  "Driscolls", 					
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(4));
	}
	
	
	@Test
	@Rollback(false)
	public void whenSearchCashews_thenReturnCashewsProducts() {
		
		// when
		Page<Product> 
						pp = productService.search(
							  "en-GB", 
							  "HKD", 
							  "Ignored",
							  0,
							  10, 
							  "nameAsc",
							  "Cashews",						
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(1));
	}
	
	@Test
	@Rollback(false)
	public void whenSearchForBrandGlorysFruit_thenReturnBrandGlorysFruitProducts() {
		returnFacets.clear();
		
		// when
		productService.search( 	"en-GB", 
								"HKD", 
								"Ignored",
								  0,
								  10, 
								  "nameAsc",
								"Fruit", 
								facetPayload,
								returnFacets);
		
		Set<IFacet> fp = returnFacets.stream().filter(f -> f.getFacetingName().equals("brand")
									   && f.getValue().equals("GLO01")).collect(Collectors.toSet());

		returnFacets.clear();
		
		Page<Product> pp = 
		productService.search( 	"en-GB", 
								"HKD", 
								"Ignored",
								  0,
								  10, 
								  "nameAsc",
								"Fruit", 
								fp,
								returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(2));
	}
	
	@Test
	@Rollback(false)
	public void whenSearchForBrandGlorysAndPlantersFruit_thenReturnBrandGlorysAndPlantersFruitProducts() {

		// when
		Page<Product> pp = 
		productService.search( 	"en-GB", 
								"HKD", 
								"Ignored",
								  0,
								  10,
								  "nameAsc",
								"Fruit", 		
								facetPayload,
								returnFacets);
		
		Set<IFacet> fp = returnFacets.stream().filter(f -> f.getFacetingName().equals("brand")
									   && 
									   (f.getValue().equals("GLO01")
									   || 
									    f.getValue().equals("PLA01")
									   )).collect(Collectors.toSet());
		
		// then
		assertThat(pp.getTotalPages())
		 .isEqualTo(2);
		assertThat(pp.getTotalElements())
		.isEqualTo(new Long(12));
		assertThat(returnFacets.size())
		.isEqualTo(23);
		
		returnFacets.clear();
		
		pp = 
		productService.search( 	"en-GB", 
								"HKD", 
								"Ignored",
								  0,
								  10,
								  "nameAsc",
								"Fruit", 
								fp,
								returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(4));
    	assertThat(returnFacets.size())
		.isEqualTo(13);
	}
	
	@Test
	@Rollback(false)
	public void whenSearchForKeywordPrettyGirlWithBestMatch_thenReturnResultsInCorrectOrder() {

		// when
		Page<Product> pp = 
		productService.search( 	"en-GB", 
								"HKD", 
								"Ignored",
								  0,
								  1,
								  "bestMatch",
								"Pretty Girl", 		
								facetPayload,
								returnFacets);
		
		// then
		assertThat(pp.getTotalPages())
		 .isEqualTo(7);
		
		assertThat(pp.getTotalElements())
		.isEqualTo(new Long(7));
		
		assertThat(pp.stream().findFirst().get().getProductUPC()).isEqualTo("30833047");
		
		assertThat(pp.stream().findFirst().get().getProductDesc()).isEqualTo("Pretty Girl Hair Sticker ");
		
	}
	
	
	@Test
	@Rollback(false)
	public void whenEnterSearchTermKor_thenReturnCorrectSuggestions() {
		
		// when
		String[] sp = productService.getSuggestion("kor", Constants.localeENGB, Constants.currencyUSD);
		
        // then
		assertThat(sp.length)
        .isEqualTo(12);
	}
	
	@Test
	@Rollback(false)
	public void whenEnterSearchTermPretty_thenReturnCorrectSuggestions() {
		
		// when
		String[] sp = productService.getSuggestion("pretty", Constants.localeENGB, Constants.currencyUSD);
		
        // then
		assertThat(sp.length)
        .isEqualTo(7);
	}
	
	@Test
	@Rollback(false)
	public void whenEnterSearchTermApple_thenReturnCorrectSuggestions() {
		
		// when
		String[] sp = productService.getSuggestion("apple", Constants.localeENGB, Constants.currencyUSD);
		
		System.out.println("results = " + String.join(",", sp));
        // then
		assertThat(sp.length)
        .isEqualTo(1);
	}
	
}