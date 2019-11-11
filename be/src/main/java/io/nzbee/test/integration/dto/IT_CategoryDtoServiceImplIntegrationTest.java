package io.nzbee.test.integration.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.dto.category.ICategoryService;
import io.nzbee.dto.category.product.ProductCategory;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.category.CategoryServiceImpl;

@RunWith(SpringRunner.class)
public class IT_CategoryDtoServiceImplIntegrationTest {

	@TestConfiguration
    static class CategoryDtoServiceImplIntegrationTest {
		//the beans that we need to run this integration test
        @Bean(value = "categoryDtoService")
        public ICategoryService categoryDtoService() {
            return new CategoryServiceImpl();
        }
        
        @Bean(value = "categoryEntityService")
        public io.nzbee.entity.category.ICategoryService categoryService() {
            return new io.nzbee.entity.category.CategoryServiceImpl();
        }
    }

	@Autowired
    private ICategoryService categoryDtoService;
 
	@MockBean
    private io.nzbee.entity.category.ICategoryService categoryEntityService;
	
    @Before
    public void setUp() {
    	//we setup a mock so that when 
    	MockitoAnnotations.initMocks(this);
    	
        io.nzbee.entity.category.Category testCategory = new CategoryProduct();
        testCategory.setCategoryCode("TST01");
        //need to fill more of the properties here
        
        Mockito.when(categoryEntityService.findByCode("en-GB", "HKD", testCategory.getCategoryCode()))
          .thenReturn(Optional.ofNullable(testCategory));
    }
    
    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String code = "TST01";
        
        Optional<Category> found = categoryDtoService.findByCode("en-GB", "HKD", code);
      
        
         assertThat(found.get().getCategoryCode())
          .isEqualTo(code);
     }
    
}
