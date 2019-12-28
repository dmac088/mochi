package io.nzbee.test.unit.dto;

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
public class UT_BrandCategoryTest {

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
    	final Category testCategory = categoryEntityBeanFactory.getBrandCategoryEntityBean();
    	
        Mockito.when(categoryDao.findByCode("en-GB", "HKD", "TST02"))
          .thenReturn(Optional.ofNullable(testCategory));
        
        Mockito.when(categoryDao.findById("en-GB", "HKD", new Long(1)))
        .thenReturn(Optional.ofNullable(testCategory));
        
        Mockito.when(categoryDao.findByDesc("en-GB", "HKD", "test brand category"))
        .thenReturn(Optional.ofNullable(testCategory));
    }
   
    
    @Test
    public void whenValidId_thenBrnadCategoryShouldBeFound() {
        Long Id = new Long(1);
        Optional<Category> found = categoryService.findById("en-GB", "HKD", Id);
      
        assertFound(found.get());
     }
    
    @Test
    public void whenValidCode_thenBrandCategoryShouldBeFound() {
        String code = "TST02";
        Optional<Category> found = categoryService.findByCode("en-GB", "HKD", code);
      
        assertFound(found.get());
     }
    
    @Test
    public void whenValidDesc_thenBrandCategoryShouldBeFound() {
    
        Optional<Category> found = categoryService.findByDesc("en-GB", "HKD", "test brand category");
      
        assertFound(found.get());
     }
    
    private void assertFound(final Category found) {

    	assertThat(found.getCategoryCode())
        .isEqualTo("TST02");
    	
	    assertThat(found.getCategoryLevel())
	    .isEqualTo(new Long(2));
	    
//	    assertThat(found.getCategoryType().getCode())
//	    .isEqualTo("TST01");
	    
	    assertThat(found.getCategoryAttribute().getCategoryDesc())
	    .isEqualTo("test brand category");
    }
    
}
