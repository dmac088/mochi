package io.nzbee.test.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.CategoryDaoPostgresImpl;
import io.nzbee.entity.category.CategoryServiceImpl;
import io.nzbee.entity.category.ICategoryDao;

@RunWith(SpringRunner.class)
public class IT_CategoryServiceImplIntegrationTest {

	@TestConfiguration
    static class CategoryServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
        @Bean(value = "categoryEntityService")
        public ICategoryService categoryService() {
            return new CategoryServiceImpl();
        }
        
        @Bean(value = "categoryEntityPostgresDao")
        public ICategoryDao categoryDao() {
            return new CategoryDaoPostgresImpl();
        }

    }

	@Autowired
    private ICategoryService categoryService;
 
	@MockBean
    private ICategoryDao categoryDao;
	
    @Before
    public void setUp() {
    	//we setup a mock so that when 
    	MockitoAnnotations.initMocks(this);
    	
        Category testCategory = new CategoryProduct();
        testCategory.setCategoryCode("TST01");
     
        Mockito.when(categoryDao.findByCode("en-GB", "HKD", testCategory.getCategoryCode()))
          .thenReturn(Optional.ofNullable(testCategory));
    }
    
    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String code = "TST01";
        Optional<Category> found = categoryService.findByCode("en-GB", "HKD", code);
      
         assertThat(found.get().getCategoryCode())
          .isEqualTo(code);
     }
    
}
