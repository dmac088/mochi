package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.brand.CategoryBrand;
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
public class IT_CategoryEntityRespoitoryIntegrationTest {

	@TestConfiguration
    static class CategoryEntityRepositoryIntegrationTest {
        
        @Bean(value = "categoryEntityService")
        public io.nzbee.entity.category.ICategoryService categoryService() {
            return new io.nzbee.entity.category.CategoryServiceImpl();
        }
        
    }
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;
	
 
    @Autowired
    private ICategoryService categoryService;
    
    @Test
    public void whenFindAll_thenReturnAllCategories() {
    	
        // when
    	List<Category> found = categoryService.findAll( GeneralVars.LANGUAGE_ENGLISH, 
												  		GeneralVars.CURRENCY_USD);
     
        // then
    	assertFound(found);
    }
    
    @Test
    public void whenFindAllBrandCategories_thenReturnAllBrandCategories() {
    	
        // when
    	List<Category> found = categoryService.findAll( GeneralVars.LANGUAGE_ENGLISH, 
												  		GeneralVars.CURRENCY_USD,
												  		CategoryBrand.class);
     
        // then
    	assertFound(found);
    }
	
    private void assertFound(final List<Category> found) {
    	
    	assertThat(found).isNotNull();
    //	assertThat(found).size().isEqualTo(43);
    }
    
}
