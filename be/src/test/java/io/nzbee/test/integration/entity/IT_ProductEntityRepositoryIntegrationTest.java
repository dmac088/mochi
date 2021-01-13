package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import org.springframework.data.domain.Page;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.category.product.CategoryProductDTO;
import io.nzbee.entity.category.product.ICategoryProductService;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductDTO;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.test.integration.entity.beans.product.IProductEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
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
public class IT_ProductEntityRepositoryIntegrationTest {

	@TestConfiguration
    static class ProductDTORepositoryIntegrationTest {
        
    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private IProductEntityBeanFactory productEntityBeanFactory;
 
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryProductService productCategoryService;
    
    private ProductEntity product = null;
    
    @Transactional
	public ProductEntity persistNewProduct() {
    	
		product = productEntityBeanFactory.getBean();
	    
		productService.save(product);
	    	
	    return product;
	}
	
	@Before
	public void persistANewProduct() {
		this.persistNewProduct();
	}
	
	@Test
	public void whenFindById_thenReturnProduct() {
		 // when
    	Optional<ProductDTO> found = productService.findById( Constants.localeENGB, 
				  								  	Constants.currencyUSD,  
				  								  	product.getProductId());
     
        // then
    	assertFound(found);
	}
	
	
	@Test
	public void whenFindByCode_thenReturnProduct() {
		 // when
    	Optional<ProductDTO> found = productService.findByCode(Constants.localeENGB, 
				  								  	 Constants.currencyUSD,  
												     "123456789");
    	
        // then
    	assertFound(found);
	}
	
	@Test
	public void whenFindByDesc_thenReturnProduct() {
		 // when
    	Optional<ProductDTO> found = productService.findByDesc(Constants.localeENGB, 
				  								  	 Constants.currencyUSD,  
												  	 "test product");
     
        // then
    	assertFound(found);
	}
	
	@Test
    public void whenFindByProductCode_thenReturnProductCategories() {
    	
        // when
    	List<CategoryProductDTO> found = productCategoryService.findAllByProductCode(Constants.localeENGB, 
				 																  	"123456789");
     
        //then
    	assertThat(found.size()).isEqualTo(2);
    }
	
	@Test
    public void whenFindForFruitCategory_thenReturnAllFruitProducts() {
    	
        // when
    	Page<ProductDTO> found =		 productService.findAll( Constants.localeENGB, 
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
    public void whenFindForFruitCategoryWithNullPrice_thenReturnAllFruitProducts() {
    	
        // when
    	Page<ProductDTO> found =		 productService.findAll( Constants.localeENGB, 
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
    public void whenFindForPomesCategory_thenReturnAllPomesProducts() {
    	
		Set<String> categories = new HashSet<String>();
		categories.add("POM01");
		
        // when
    	Page<ProductDTO> found =		 productService.findAll( Constants.localeENGB, 
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
    public void whenFindForFruitWithOrganicTag_thenReturnAllOrganicFruitProducts() {
    	
		Set<String> tags = new HashSet<String>();
		tags.add("ORG01");
		
        // when
    	Page<ProductDTO> found =		 productService.findAll( Constants.localeENGB, 
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
    public void whenFindForFruitWithBrandEnza_thenReturnAllEnzaFruitProducts() {
    	
		Set<String> brands = new HashSet<String>();
		brands.add("ENZ01");
		
        // when
    	Page<ProductDTO> found =		 productService.findAll( Constants.localeENGB, 
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
    public void whenFindForBrandEnza_thenReturnAllEnzaProducts() {
    	
		Set<String> brands = new HashSet<String>();
		brands.add("ENZ01");
		
        // when
    	Page<ProductDTO> found =		 productService.findAll( Constants.localeENGB, 
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
	
	 
    private void assertFound(Optional<ProductDTO> found) {
    	
    	assertNotNull(found);
    	
    	assertTrue(found.isPresent());
    	
    	assertThat(found.get().getProductUPC())
        .isEqualTo("123456789");
    	
    	assertThat(found.get().getDepartment().getDepartmentCode())
    	.isEqualTo("ACC01");
    	
    	assertThat(found.get().getProductStatusCode())
    	.isEqualTo("ACT01");
    	
    	assertThat(found.get().getBrand().getBrandCode())
    	.isEqualTo("PLA01");
    	
    	assertNotNull(found.get().getCategories());
    	assertThat(found.get().getCategories().size()).isEqualTo(2);
    	assertThat(found.get().getCategories().stream().filter(f -> f.getCategoryCode().equals("POM01")).findAny().isPresent()).isTrue();
    	assertThat(found.get().getCategories().stream().filter(f -> f.getCategoryCode().equals("CIT01")).findAny().isPresent()).isTrue();
    	
    	assertThat(found.get().getRetailPrice())
    	.isEqualTo(new Double(7.8));
    	
    	assertThat(found.get().getMarkdownPrice())
    	.isEqualTo(new Double(6.45));
    	
    	assertThat(found.get().getPromotions().size()).isEqualTo(1);
    	
//    	assertThat(found.getTags().stream().filter(f -> f.getTagCode().equals("ORG01")).findFirst().isPresent()).isTrue();
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}
