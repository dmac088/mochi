package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
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
		
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	private static boolean setUpIsDone = false;
	
	private static Category category = null;
	
	@Before
	public void setUp() {
		if (setUpIsDone) {
			return;
		}
		try (Connection con = database.getConnection()) {
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_schema.sql"));
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_data.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.persistNewCategory();
		setUpIsDone = true;
	}
	
	public Category persistNewCategory() {
    	
		category = categoryDoBeanFactory.getBean();
	    	
	    categoryService.save(category);
	    	
	    return category;
	}
   
    
    @Test
    @Rollback(false)
    public void whenValidCode_thenProductCategoryShouldBeFound() {
        Category found = categoryService.findByCode(Constants.localeENGB, "TST01");
      
        assertFound(found);
    }
    
    
    @Test
    @Rollback(false)
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
