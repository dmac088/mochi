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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.dto.category.ICategoryService;
import io.nzbee.test.integration.entity.beans.CategoryEntityBeanFactory;
import io.nzbee.dto.category.Category;
import io.nzbee.dto.category.CategoryServiceImpl;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
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
        
        @Bean(value = "categoryEntityBeanFactory")
        public CategoryEntityBeanFactory categoryFactoryBean() {
            return new CategoryEntityBeanFactory();
        }
    }

	@Autowired
    private ICategoryService categoryDtoService;
	
	@Autowired
	private CategoryEntityBeanFactory categoryEntityBeanFactory;
 
	@MockBean
    private io.nzbee.entity.category.ICategoryService categoryEntityService;
	
    @Before
    public void setUp() {
    	//we setup a mock so that when 
    	MockitoAnnotations.initMocks(this);
        
        io.nzbee.entity.category.Category category = categoryEntityBeanFactory.getCategoryEntityBean();
        
        //need to fill more of the properties here
        Mockito.when(categoryEntityService.findByCode("en-GB", "HKD", category.getCategoryCode()))
          .thenReturn(Optional.ofNullable(category));
    }
    
    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String code = "TST01";
        String desc = "testCategory";
        
        Optional<Category> found = categoryDtoService.findByCode("en-GB", "HKD", code);
      
         assertThat(found.get().getCategoryCode())
          .isEqualTo(code);
         assertThat(found.get().getCategoryDesc())
          .isEqualTo(desc);
     }
    
}
