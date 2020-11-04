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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.category.CategoryDTO;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.brand.CategoryBrandDTO;
import io.nzbee.resources.product.ProductLightResource;
import io.nzbee.test.integration.beans.CategoryEntityBeanFactory;

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
public class IT_BrandCategoryEntityRepositoryIntegrationTest {
 
	@TestConfiguration
    static class BrandCategoryEntityRepositoryIntegrationTest {
            
        @Bean(value = "pagedProductResourceAssembler")
        public PagedResourcesAssembler<ProductLightResource> pagedProductResourceAssembler() {
        	return new PagedResourcesAssembler<ProductLightResource>(null, null);
        }
        
        @Bean(value = "pagedProductAssembler")
        public PagedResourcesAssembler<Product> pagedProductAssembler() {
        	return new PagedResourcesAssembler<Product>(null, null);
        }
        
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private CategoryEntityBeanFactory categoryEntityBeanFactory;
 
    @Autowired
    private ICategoryService categoryService;
 
	private io.nzbee.entity.category.CategoryEntity category = null;
	
	@MockBean
    private JavaMailSender mailSender;
    
    @Before
    public void setUp() { 
    	this.persistNewCategory();
    }
    
	public io.nzbee.entity.category.CategoryEntity persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getBrandCategoryEntityBean();
	    	
	    //persist a new transient test category
	    entityManager.persist(category);
	    entityManager.flush();
	    
	    return category;
	}
   
    
    @Test
    public void whenFindById_thenReturnBrandCategory() {
    	
        // when
    	CategoryDTO found = categoryService.findById(Constants.localeENGB, 
												  category.getCategoryId()).get();
     
        // then
    	assertFound(found);
    }
    
    // write test cases here
    @Test
    public void whenFindByCode_thenReturnBrandCategory() {
    	
        // when
    	CategoryDTO found = categoryService.findByCode(Constants.localeENGB, 
				 									"TST02").get();
     
        // then
    	assertFound(found);
    }
    
 // write test cases here
    @Test
    public void whenFindByDesc_thenReturnBrandCategory() {
    	
        // when
    	CategoryDTO found = categoryService.findByDesc(Constants.localeENGB, 
				 									"test brand category").get();
     
        //then
    	assertFound(found);
    }
    
    private void assertFound(final CategoryDTO found) {
    	
    	CategoryBrandDTO cb = (CategoryBrandDTO) found;
    	
    	assertThat(cb.getCategoryCode())
        .isEqualTo("TST02");
    	
	    assertThat(cb.getBrandCount())
	    .isEqualTo(new Long(2));
	    
	    assertThat(cb.getCategoryDesc())
	    .isEqualTo("test brand category");
	    
	  
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
 
}