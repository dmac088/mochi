package io.nzbee.test.unit.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
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
import io.nzbee.Constants;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.entity.adapters.PostgresCategoryAdapter;
import io.nzbee.test.unit.domain.beans.category.CategoryDoBeanFactory;
import io.nzbee.test.unit.domain.beans.category.ICategoryDoBeanFactory;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "ut")
public class UT_ProductCategoryTest {

	@TestConfiguration
	static class ProductCategoryDomainServiceImplUnitTest {
		// the beans that we need to run this test
		
		@Bean
		public CategoryDoBeanFactory categoryDoBeanFactory() {
			return new CategoryDoBeanFactory();
		}
		
		@Bean
		public ICategoryPortService categoryPortService() {
			return new PostgresCategoryAdapter();
		}
		
		@Bean 
		public ICategoryService categoryService() {
			return new CategoryServiceImpl();
		}
		
	}
	
	@Autowired
	private ICategoryService categoryService;

	@MockBean
	private ICategoryPortService categoryPortService;

	@Autowired
	private ICategoryDoBeanFactory categoryDoBeanFactory;
	
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.initMocks(this);

		Category category = categoryDoBeanFactory.getProductCategoryDoBean();

		// need to fill more of the properties here
		Mockito.when(categoryPortService.findByCode(Constants.localeENGB,
												category.getCategoryCode())).thenReturn(category);
		
		Mockito.when(categoryPortService.findByDesc(Constants.localeENGB,
												category.getCategoryDesc())).thenReturn(category);
	}

	@Test
	public void whenFindByCode_thenProductCategoryIsFound() {
		String code = "TST01";

		Category found = categoryService.findByCode(Constants.localeENGB,
													  code);

		assertFound(found);
	}
	
	@Test
	public void whenFindByDesc_thenProductCategoryIsFound() {
		String desc = "test product category";

		Category found = categoryService.findByDesc(Constants.localeENGB,
													  desc);

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
