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
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.test.integration.beans.CategoryEntityBeanFactory;
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
public class IT_ProductCategoryEntityRepositoryIntegrationTest {
 
	@TestConfiguration
    static class ProductCategoryEntityRepositoryIntegrationTest {
        
        @Bean(value = "categoryEntityService")
        public io.nzbee.entity.category.ICategoryService categoryService() {
            return new io.nzbee.entity.category.CategoryServiceImpl();
        }
        
        @Bean(value = "categoryEntityBeanFactory")
        public CategoryEntityBeanFactory categoryFactoryBean() {
            return new CategoryEntityBeanFactory();
        }
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
	@Autowired
	private CategoryEntityBeanFactory categoryEntityBeanFactory;
 
    @Autowired
    private ICategoryService categoryService;
    
    private io.nzbee.entity.category.Category category = null;
    
    @Before
    public void setUp() { 
    	this.persistNewCategory();
    }
    
    
	public io.nzbee.entity.category.Category persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getProductCategoryEntityBean();
	    
	    //persist a new transient test category
	    entityManager.persist(category);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return category;
	}
   
    
    @Test
    public void whenFindById_thenReturnProductCategory() {
    	
        // when
    	Category found = categoryService.findById(GeneralVars.LANGUAGE_ENGLISH, 
												  GeneralVars.CURRENCY_USD,  
												  category.getCategoryId()).get();
     
        // then
    	assertFound(found);
    }
    
    @Test
    public void whenFindByCode_thenReturnProductCategory() {
    	
        // when
    	Category found = categoryService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
				 									GeneralVars.CURRENCY_USD, 
				 									"TST02").get();
     
        // then
    	assertFound(found);
    }
    @Test
    public void whenFindByDesc_thenReturnProductCategory() {
    	
        // when
    	Category found = categoryService.findByDesc(GeneralVars.LANGUAGE_ENGLISH, 
				 									GeneralVars.CURRENCY_USD, 
				 									"test product category").get();
     
        //then
    	assertFound(found);
    }
    
    private void assertFound(final Category found) {
    	
    	System.out.println(found.getTypeDiscriminator());
    	
    	assertThat(found.getCategoryCode())
        .isEqualTo("TST02");
	    assertThat(found.getCategoryLevel())
	    .isEqualTo(new Long(1));
	    assertThat(found.getCategoryType().getCode())
	    .isEqualTo("PRD01");
	    assertThat(found.getCategoryAttribute().getCategoryDesc())
	    .isEqualTo("test product category");
    }
 
    @After
    public void closeConnection() {
    	entityManager.close();
    }
}