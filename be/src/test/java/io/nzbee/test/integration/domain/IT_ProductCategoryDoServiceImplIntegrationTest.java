package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.test.integration.domain.beans.category.CategoryDoBeanFactory;
import io.nzbee.test.integration.domain.beans.category.ICategoryDoBeanFactory;
import io.nzbee.Constants;
import io.nzbee.domain.category.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
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
public class IT_ProductCategoryDoServiceImplIntegrationTest {
	
	@TestConfiguration
	static class ProductCategoryDoServiceImplIntegrationTest_Configuration {
		// the beans that we need to run this test
		@Bean
		public ICategoryDoBeanFactory categoryDoBeanFactory() {
			return new CategoryDoBeanFactory();
		}
		
	}
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
    private ICategoryPortService categoryService;
	
	@Autowired
	private ICategoryDoBeanFactory categoryDoBeanFactory;
	
	private Category category = null;
	
	public Category persistNewCategory() {
    	
		category = categoryDoBeanFactory.getBean();
	    	
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
    
    
    @Test
    public void whenValidDesc_thenProductCategoryShouldBeFound() {
        Category found = categoryService.findByDesc(Constants.localeENGB, "test product category");
      
        assertFound(found);
     }
    
    private void assertFound(Category found) {

    	assertNotNull(found);
    	
    	assertThat(found.getCategoryCode())
        .isEqualTo("TST01");
    	
	    assertThat(found.getCategoryDesc())
	    .isEqualTo("test product category");
    }

    
}
