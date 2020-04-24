package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.hibernate.CacheMode;
import org.hibernate.search.batchindexing.impl.SimpleIndexingProgressMonitor;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
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
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.IFacetService;
import io.nzbee.domain.brand.BrandServiceImpl;
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.domain.tag.TagServiceImpl;
import io.nzbee.entity.product.Product;
import io.nzbee.test.integration.beans.ProductEntityBeanFactory;
import io.nzbee.ui.component.web.facet.IFacet;
import io.nzbee.ui.component.web.search.ISearchService;

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
public class IT_ProductEntitySearchIntegrationTest {

	@TestConfiguration
    static class ProductEntityRepositoryIntegrationTest {
        
        @Bean(value = "productEntityService")
        public io.nzbee.entity.product.IProductService productService() {
            return new io.nzbee.entity.product.ProductServiceImpl();
        }
        
        @Bean(value = "productEntityBeanFactory")
        public ProductEntityBeanFactory productFactoryBean() {
            return new ProductEntityBeanFactory();
        }
        
        @Bean(value = "categoryDomainService")
        public CategoryServiceImpl getCategoryService() {
        	return new CategoryServiceImpl();
        }
        
        @Bean(value = "brandDomainService")
        public BrandServiceImpl getBrandService() {
        	return new BrandServiceImpl();
        }
        
        @Bean(value = "tagDomainService")
        public TagServiceImpl getTagService() {
        	return new TagServiceImpl();
        }
     
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
    
	@Autowired
    @Qualifier(value = "SearchService")
    private ISearchService searchService;
	
	@Autowired
	List<IFacetService> facetServices;
   
	private Set<IFacet> facetPayload = new HashSet<IFacet>();
	private Set<IFacet> returnFacets = new HashSet<IFacet>();
	
	@Before
	public void buildSearchIndex() {
		FullTextEntityManager fullTextEntityManager 
		  = Search.getFullTextEntityManager(entityManager);
		try {
			fullTextEntityManager
			.createIndexer( Product.class )
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
	}
	
	
	@Test
	public void whenSearchFruit_thenReturnFruitProducts() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Fruit", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(2);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(12));
	}
	
	@Test
	public void whenSearchFruit_thenReturnFruitProductsZHHK() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "zh-HK", 
							  "HKD", 
							  "Ignored", 
							  "水果", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(2);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(12));
	}
	

	@Test
	public void whenSearchVegetables_thenReturnVegetableProducts() {
		
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Vegetables", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(2);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(12));
	}
	
	@Test
	public void whenSearchApple_thenReturnAppleProduct() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Apple", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(1));
	} 
	
	@Test
	public void whenSearchCauliflower_thenReturnCauliflowerProduct() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Cauliflower", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(1));
	} 
	
	@Test
	public void whenSearchAll_thenReturnAllProducts() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "All", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(3);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(29));
	} 
	
	@Test
	public void whenSearchNuts_thenReturnNuts() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Nuts", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(5));
	} 
	
	@Test
	public void whenSearchOrganic_thenReturnOrganicProducts() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Organic", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(2));
	} 
	
	@Test
	public void whenSearchOrganic_thenReturnOrganicProductsZHHK() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "zh-HK", 
							  "HKD", 
							  "Ignored", 
							  "有機", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(2));
	} 
	
	@Test
	public void whenSearchOrganic_thenReturnOrganicGlutenFreeZHHK() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "zh-HK", 
							  "HKD", 
							  "Ignored", 
							  "不含麩質", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(5));
	} 
	
	@Test
	public void whenSearchGlutenFree_thenReturnGlutenFreeProducts() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Gluten Free", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(5));
	}
	
	@Test
	public void whenSearchGlorys_thenReturnGlorysProducts() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Glorys", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(4));
	}
	
	@Test
	public void whenSearchEnza_thenReturnEnzaProducts() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Enza", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(4));
	}
	
	@Test
	public void whenSearchPlanters_thenReturnPlantersProducts() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Planters", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(4));
	}
	
	@Test
	public void whenSearchPlanters_thenReturnDoleProducts() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Dole", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(3));
	}
	
	@Test
	public void whenSearchPlanters_thenReturnDriscollsProducts() {
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Driscolls", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(4));
	}
	
	
	@Test
	public void whenSearchCashews_thenReturnCashewsProducts() {
		
		// when
		Page<io.nzbee.domain.product.Product> 
						pp = searchService.findAll(
							  "en-GB", 
							  "HKD", 
							  "Ignored", 
							  "Cashews", 
							  0, 
							  10, 
							  "", 
							  facetPayload,
							  returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(1));
	}
	
	@Test
	public void whenSearchForBrandGlorysFruit_thenReturnBrandGlorysFruitProducts() {
		returnFacets.clear();
		
		// when
		searchService.findAll( 	"en-GB", 
								"HKD", 
								"Ignored", 
								"Fruit", 
								0, 
								10, 
								"", 
								facetPayload,
								returnFacets);
		
		Set<IFacet> fp = returnFacets.stream().filter(f -> f.getPayloadType().equals("Brand")
									   && f.getValue().equals("GLO01")).collect(Collectors.toSet());

		returnFacets.clear();
		
		Page<io.nzbee.domain.product.Product> pp = 
		searchService.findAll( 	"en-GB", 
								"HKD", 
								"Ignored", 
								"Fruit", 
								0, 
								10, 
								"", 
								fp,
								returnFacets);
		
		
        // then
		assertThat(pp.getTotalPages())
        .isEqualTo(1);
    	assertThat(pp.getTotalElements())
        .isEqualTo(new Long(2));
	}
	
	@Test
	public void whenSearchForBrandGlorysAndPlantersFruit_thenReturnBrandGlorysAndPlantersFruitProducts() {

		// when
		Page<io.nzbee.domain.product.Product> pp = 
		searchService.findAll( 	"en-GB", 
								"HKD", 
								"all", 
								"Fruit", 
								0, 
								10, 
								"", 
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
		.isEqualTo(19);
		
		returnFacets.clear();
		
		pp = 
		searchService.findAll( 	"en-GB", 
								"HKD", 
								"Ignored", 
								"Fruit", 
								0, 
								10, 
								"", 
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
	
	@After
	public void closeConnection() {
	 	entityManager.close();
	}
}
