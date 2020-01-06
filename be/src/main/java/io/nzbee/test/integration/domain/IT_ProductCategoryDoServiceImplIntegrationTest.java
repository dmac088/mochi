package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import javax.persistence.EntityManager;
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
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.entity.category.CategoryDaoPostgresImpl;
import io.nzbee.entity.category.ICategoryDao;
import io.nzbee.test.integration.beans.CategoryEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
public class IT_ProductCategoryDoServiceImplIntegrationTest {

	@TestConfiguration
    static class CategoryServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
        @Bean(value = "categoryDomainService")
        public ICategoryService categoryService() {
            return new CategoryServiceImpl();
        }
        
        @Bean(value = "categoryEntityService")
        public io.nzbee.entity.category.ICategoryService categoryEntityService() {
        	return new io.nzbee.entity.category.CategoryServiceImpl();
        }
        
        @Bean(value = "categoryEntityPostgresDao")
        public ICategoryDao categoryDao() {
            return new CategoryDaoPostgresImpl();
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
    private ICategoryService categoryService;
	
	@Autowired
	private CategoryEntityBeanFactory categoryEntityBeanFactory;
	
	private io.nzbee.entity.category.Category category = null;
	
	public io.nzbee.entity.category.Category persistNewCategory() {
    	
		category = categoryEntityBeanFactory.getProductCategoryEntityBean();
	    	
	    //persist a new transient test hierarchy
	    entityManager.persist(category.getHierarchy());
	    	
	    //persist a new transient test category
	    entityManager.persist(category);
	    entityManager.flush();
	    entityManager.close();
	    	
	    return category;
	}
	
    @Before
    public void setUp() {
    	
    	this.persistNewCategory();
    	
    }
   
    
    @Test
    public void whenValidCode_thenProductCategoryShouldBeFound() {
        String code = "TST02";
        Category found = categoryService.findByCode("en-GB", "HKD", code);
      
        assertFound(found);
     }
    
    @Test
    public void whenValidDesc_thenProductCategoryShouldBeFound() {
    	String desc = "test product category";
        Category found = categoryService.findByDesc("en-GB", "HKD", desc);
      
        assertFound(found);
     }
    
    private void assertFound(final Category found) {

    	assertThat(found.getCode())
        .isEqualTo("TST02");
    	
	    assertThat(found.getLevel())
	    .isEqualTo(new Long(1));
	    
	    assertThat(found.getDesc())
	    .isEqualTo("test product category");
    }
    
}
