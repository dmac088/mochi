package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.test.integration.beans.CategoryDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "dev")
public class IT_ProductCategoryDoServiceImplIntegrationTest {

	@TestConfiguration
    static class CategoryServiceImplIntegrationTestConfiguration {
		
    }

	@Autowired
    private ICategoryService categoryService;
	
	@Autowired
	private CategoryDoBeanFactory categoryDoBeanFactory;
	
	private ProductCategory category = null;
	
	public ProductCategory persistNewCategory() {
    	
		category = categoryDoBeanFactory.getProductCategoryDoBean();
	    	
	    categoryService.save(category);
	    	
	    return category;
	}
	
    @Before
    public void setUp() {
    	this.persistNewCategory();
    }
   
    
    @Test
    public void whenValidCode_thenProductCategoryShouldBeFound() {
        Category found = categoryService.findByCode("en-GB", "HKD", "TST01").get();
      
        assertFound(found);
     }
    
    @Test
    public void whenValidDesc_thenProductCategoryShouldBeFound() {
        Category found = categoryService.findByDesc("en-GB", "HKD", "test product category").get();
      
        assertFound(found);
     }
    
    private void assertFound(final Category found) {

    	assertThat(found.getCode())
        .isEqualTo("TST01");
    	
	    assertThat(found.getLevel())
	    .isEqualTo(new Long(2));
	    
	    assertThat(found.getDesc())
	    .isEqualTo("test product category");
    }
    
}
