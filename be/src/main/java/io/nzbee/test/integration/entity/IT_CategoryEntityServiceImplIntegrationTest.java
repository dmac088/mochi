package io.nzbee.test.integration.entity;

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
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.test.entity.beans.CategoryEntityBeanFactory;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.CategoryDaoPostgresImpl;
import io.nzbee.entity.category.CategoryServiceImpl;
import io.nzbee.entity.category.ICategoryDao;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
public class IT_CategoryEntityServiceImplIntegrationTest {

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
        
        @Bean(value = "categoryEntityBeanFactory")
        public CategoryEntityBeanFactory categoryFactoryBean() {
            return new CategoryEntityBeanFactory();
        }

    }

	@Autowired
    private ICategoryService categoryService;
 
	@MockBean
    private ICategoryDao categoryDao;
	
	@Autowired
	private CategoryEntityBeanFactory categoryEntityBeanFactory;
	
    @Before
    public void setUp() {
    	//we setup a mock so that when 
    	MockitoAnnotations.initMocks(this);
    	final Category testCategory = categoryEntityBeanFactory.getCategoryEntityBean();
    	
        Mockito.when(categoryDao.findByCode("en-GB", "HKD", "TST02"))
          .thenReturn(Optional.ofNullable(testCategory));
        
        Mockito.when(categoryDao.findByDesc("en-GB", "HKD", "test category"))
        .thenReturn(Optional.ofNullable(testCategory));
    }
    
    @Test
    public void whenValidCode_thenCategoryShouldBeFound() {
        String code = "TST02";
        Optional<Category> found = categoryService.findByCode("en-GB", "HKD", code);
      
         assertThat(found.get().getCategoryCode())
          .isEqualTo(code);
     }
    
    @Test
    public void whenValidDesc_thenCategoryShouldBeFound() {
        String desc = "test category";
        Optional<Category> found = categoryService.findByDesc("en-GB", "HKD", desc);
      
         assertThat(found.get().getCategoryAttribute().getCategoryDesc())
          .isEqualTo(desc);
     }
    
}
