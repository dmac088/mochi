package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
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
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.test.entity.beans.CategoryEntityBeanFactory;
import io.nzbee.variables.GeneralVars;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
public class IT_CategoryEntityRepositoryIntegrationTest {
 
	@TestConfiguration
    static class CategoryDtoServiceImplIntegrationTest {
        
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
    
    
    public Category persistNewCategory() {
    	
    	final Category category = categoryEntityBeanFactory.getProductCategoryEntityBean();
    	
    	//persist a new transient test category type
    	//entityManager.persist(category.getCategoryType());
    	
    	//persist a new transient test hierarchy
    	entityManager.persist(category.getHierarchy());
    	
    	//persist a new transient test category
    	entityManager.persist(category);
    	entityManager.flush();
    	
    	return category;
    }
   
    
    @Test
    public void whenFindById_thenReturnCategory() {
    	Category category = this.persistNewCategory();
    	
        // when
    	Category found = categoryService.findById(GeneralVars.LANGUAGE_ENGLISH, 
												  GeneralVars.CURRENCY_USD,  
												  category.getCategoryId()).get();
     
        // then
    	assertFound(found);
    }
    
    // write test cases here
    @Test
    public void whenFindByCode_thenReturnCategory() {
    	this.persistNewCategory();
    	
        // when
    	Category found = categoryService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
				 									GeneralVars.CURRENCY_USD, 
				 									"TST02").get();
     
        // then
    	assertFound(found);
    }
    
 // write test cases here
    @Test
    public void whenFindByDesc_thenReturnCategory() {
    	this.persistNewCategory();
    	
        // when
    	Category found = categoryService.findByDesc(GeneralVars.LANGUAGE_ENGLISH, 
				 									GeneralVars.CURRENCY_USD, 
				 									"test product category").get();
     
        //then
    	assertFound(found);
    }
    
    private void assertFound(final Category found) {
    	
    	assertThat(found.getCategoryCode())
        .isEqualTo("TST02");
	    assertThat(found.getCategoryLevel())
	    .isEqualTo(new Long(1));
	    assertThat(found.getCategoryType().getCode())
	    .isEqualTo("PRD01");
	    assertThat(found.getHierarchy().getHierarchyCode())
	    .isEqualTo("TST01");
	    assertThat(found.getCategoryAttribute().getCategoryDesc())
	    .isEqualTo("test product category");
    }
 
}