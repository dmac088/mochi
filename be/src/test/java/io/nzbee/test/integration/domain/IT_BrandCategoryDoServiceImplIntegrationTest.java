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
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.Constants;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.category.Category;
import io.nzbee.test.integration.beans.CategoryDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
public class IT_BrandCategoryDoServiceImplIntegrationTest {

	@TestConfiguration
    static class BrandCategoryEntityServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
 
    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
    private ICategoryPortService categoryService;
	
	@Autowired
	private CategoryDoBeanFactory categoryDoBeanFactory;
	
	private BrandCategory category = null;
	

	public BrandCategory persistNewCategory() {
	    	
		category = categoryDoBeanFactory.getBrandCategoryDoBean();
	    
	    categoryService.save(category);
	    	
	    return category;
	}
	
    @Before
    public void setUp() { 
    	this.persistNewCategory();
    }
 
    @Test
    public void whenValidCode_thenBrandCategoryShouldBeFound() {
        Category found = categoryService.findByCode(Constants.localeENGB, "TST02");
      
        assertFound(found);
    }
    
    @Test
    public void whenValidDesc_thenBrandCategoryShouldBeFound() {
        Category found = categoryService.findByDesc(Constants.localeENGB, "test brand category");
      
        assertFound(found);
     }
    
    private void assertFound(final Category found) {

    	assertThat(found.getCategoryCode())
        .isEqualTo("TST02");
    	
	    assertThat(found.getCategoryDesc())
	    .isEqualTo("test brand category");
    }
    
}