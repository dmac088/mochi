package io.nzbee.test.unit.entity;

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
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.test.entity.beans.CategoryEntityBeanFactory;
import io.nzbee.variables.GeneralVars;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
public class UT_CategoryTest {
 
	@TestConfiguration
    static class CategoryDtoServiceImplIntegrationTest {
        
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
	private CategoryEntityBeanFactory categoryEntityBeanFactory;
 
	@MockBean
    private ICategoryService categoryService;
    
	@Before
    public void setUp() {
    	//we setup a mock so that when 
    	MockitoAnnotations.initMocks(this);
        
        io.nzbee.entity.category.Category category = categoryEntityBeanFactory.getCategoryEntityBean();
        category.setCategoryId(new Long(-1));
        
        //need to fill more of the properties here
        Mockito.when(categoryService.findByCode(
        									GeneralVars.LANGUAGE_ENGLISH, 
        									GeneralVars.CURRENCY_USD, 
        									category.getCategoryCode()))
          .thenReturn(Optional.ofNullable(category));
        
        Mockito.when(categoryService.findById(
        									GeneralVars.LANGUAGE_ENGLISH, 
											GeneralVars.CURRENCY_USD, 
											new Long(-1)))
        .thenReturn(Optional.ofNullable(category));
        
        Mockito.when(categoryService.findByDesc(
							        		GeneralVars.LANGUAGE_ENGLISH, 
											GeneralVars.CURRENCY_USD, 
											category.getCategoryAttribute().getCategoryDesc()))
        .thenReturn(Optional.ofNullable(category));
    }
     
    @Test
    public void whenFindById_thenReturnCategory() {
    	
    	
        // when
    	Category found = categoryService.findById(GeneralVars.LANGUAGE_ENGLISH, 
												  GeneralVars.CURRENCY_USD,  
    											  new Long(-1)).get();
     
        // then
    	assertFound(found);
    }
    
    // write test cases here
    @Test
    public void whenFindByCode_thenReturnCategory() {
    	
        // when
    	Category found = categoryService.findByCode(GeneralVars.LANGUAGE_ENGLISH, 
				 									GeneralVars.CURRENCY_USD, 
				 									"TST02").get();
     
        // then
    	assertFound(found);
    }
    
    // write test cases here
    @Test
    public void whenFindByDesc_thenReturnCategory() {
    	
    	
        // when
    	Category found = categoryService.findByDesc(GeneralVars.LANGUAGE_ENGLISH, 
				 									GeneralVars.CURRENCY_USD, 
				 									"test category").get();
     
        //then
    	assertFound(found);
    }
    
    private void assertFound(final Category found) {

    	assertThat(found.getCategoryCode())
        .isEqualTo("TST02");
    	
	    assertThat(found.getCategoryLevel())
	    .isEqualTo(new Long(1));
	    
//	    assertThat(found.getCategoryType().getCode())
//	    .isEqualTo("TST01");
	    
	    assertThat(found.getCategoryAttribute().getCategoryDesc())
	    .isEqualTo("test category");
    }
 
}