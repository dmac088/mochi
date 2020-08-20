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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.category.ProductCategory;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.Constants;
import io.nzbee.domain.category.Category;
import io.nzbee.test.integration.beans.CategoryDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
public class IT_ProductCategoryDoServiceImplIntegrationTest {

	@TestConfiguration
    static class CategoryServiceImplIntegrationTestConfiguration {
		
    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
    private ICategoryPortService categoryService;
	
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
        Category found = categoryService.findByCode(Constants.localeENGB, "TST01");
      
        assertFound(found);
    }
    
//    @Test
//    public void whenInvalidCode_thenProductCategoryShouldNotBeFound() {
//        Optional<Category> cat = Optional.ofNullable(categoryService.findByCode(Constants.localeENGB, "ZZZZZ"));
//      
//        assertNotFound(cat);
//    }
    
    @Test
    public void whenValidDesc_thenProductCategoryShouldBeFound() {
        Category found = categoryService.findByDesc(Constants.localeENGB, "test product category");
      
        assertFound(found);
     }
    
    private void assertFound(final Category found) {

    	assertThat(found.getCategoryCode())
        .isEqualTo("TST01");
    	
//	    assertThat(found.getCategoryLevel())
//	    .isEqualTo(new Long(2));
	    
	    assertThat(found.getCategoryDesc())
	    .isEqualTo("test product category");
    }
    
//    private void assertNotFound(final Optional<Category> object) {
//
//    	assertTrue(!object.isPresent());
//    	
//    }
    
}
