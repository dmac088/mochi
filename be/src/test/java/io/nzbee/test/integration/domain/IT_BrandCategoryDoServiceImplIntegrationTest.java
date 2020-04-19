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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.domain.category.Category;
import io.nzbee.test.integration.beans.CategoryEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
public class IT_BrandCategoryDoServiceImplIntegrationTest {

	@TestConfiguration
    static class BrandCategoryEntityServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
 
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
	    	
		category = categoryEntityBeanFactory.getBrandCategoryEntityBean();
   	
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
    public void whenValidCode_thenBrandCategoryShouldBeFound() {
        String code = "TST02";
        Category found = categoryService.findByCode("en-GB", "HKD", code);
      
        assertFound(found);
    }
    
    @Test
    public void whenValidDesc_thenBrandCategoryShouldBeFound() {
        Category found = categoryService.findByDesc("en-GB", "HKD", "test brand category");
      
        assertFound(found);
     }
    
    private void assertFound(final Category found) {

    	assertThat(found.getCategoryCode())
        .isEqualTo("TST02");
    	
	    assertThat(found.getCategoryLevel())
	    .isEqualTo(new Long(2));
	    
	    assertThat(found.getCategoryDesc())
	    .isEqualTo("test brand category");
    }
    
}
