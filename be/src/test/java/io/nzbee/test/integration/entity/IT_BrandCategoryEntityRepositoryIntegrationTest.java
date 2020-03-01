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
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.product.Product;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.product.hierarchy.Hierarchy;
import io.nzbee.entity.product.hierarchy.IHierarchyRepository;
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
public class IT_BrandCategoryEntityRepositoryIntegrationTest {
 
	@TestConfiguration
    static class BrandCategoryEntityRepositoryIntegrationTest {
        
        @Bean(value = "categoryEntityService")
        public io.nzbee.entity.category.ICategoryService categoryService() {
            return new io.nzbee.entity.category.CategoryServiceImpl();
        }
        
        @Bean(value = "categoryEntityBeanFactory")
        public CategoryEntityBeanFactory categoryFactoryBean() {
            return new CategoryEntityBeanFactory();
        }
        
        @Bean(value = "pagedResourceAssembler")
        public PagedResourcesAssembler<Product> pagedResourceAssembler() {
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
    
    @Autowired
    private IHierarchyRepository hierarchyRepository;
    
	private io.nzbee.entity.category.Category category = null;
    
    @Before
    public void setUp() { 
    	this.persistNewCategory();
    }
    
	public io.nzbee.entity.category.Category persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getBrandCategoryEntityBean();
	    	
		Hierarchy h = hierarchyRepository.findByHierarchyCode("BND01");
		category.setHierarchy(h);
	    	
	    //persist a new transient test category
	    entityManager.persist(category);
	    entityManager.flush();
	    
	    return category;
	}
   
    
    @Test
    public void whenFindById_thenReturnBrandCategory() {
    	
        // when
    	Category found = categoryService.findById(GeneralVars.LANGUAGE_ENGLISH, 
												  GeneralVars.CURRENCY_USD,  
												  category.getCategoryId()).get();
     
        // then
    	assertFound(found);
    }
    
    // write test cases here
    @Test
    public void whenFindByCode_thenReturnBrandCategory() {
    	
        // when
    	Category found = categoryService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
				 									GeneralVars.CURRENCY_USD, 
				 									"TST02").get();
     
        // then
    	assertFound(found);
    }
    
 // write test cases here
    @Test
    public void whenFindByDesc_thenReturnBrandCategory() {
    	
        // when
    	Category found = categoryService.findByDesc(GeneralVars.LANGUAGE_ENGLISH, 
				 									GeneralVars.CURRENCY_USD, 
				 									"test brand category").get();
     
        //then
    	assertFound(found);
    }
    
    private void assertFound(final Category found) {
    	assertThat(found.getCategoryCode())
        .isEqualTo("TST02");
	    assertThat(found.getCategoryLevel())
	    .isEqualTo(new Long(2));
	    assertThat(found.getCategoryType().getCode())
	    .isEqualTo("BND01");
	    assertThat(found.getHierarchy().getHierarchyCode())
	    .isEqualTo("BND01");
	    assertThat(found.getCategoryAttribute().getCategoryDesc())
	    .isEqualTo("test brand category");
    }
    
    @After
    public void closeConnection() {
    	entityManager.close();
    }
 
}