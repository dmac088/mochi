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
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.test.unit.domain.beans.category.CategoryDoBeanFactory;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "ut")
public class UT_BrandCategoryTest {

	@TestConfiguration
	static class BrandCategoryDomainServiceImplUnitTest {
		// the beans that we need to run this test
		
		@Bean
		public CategoryDoBeanFactory categoryDoBeanFactory() {
			return new CategoryDoBeanFactory();
		}
		
	}

	@MockBean
	private ICategoryService categoryDoService;

	@Autowired
	private CategoryDoBeanFactory categoryDoBeanFactory;
	
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.initMocks(this);

		Category category = categoryDoBeanFactory.getBrandCategoryDoBean();

		// need to fill more of the properties here
		Mockito.when(categoryDoService.findByCode(Constants.localeENGB,
												  category.getCategoryCode())).thenReturn(category);
		
		Mockito.when(categoryDoService.findByDesc(Constants.localeENGB,
												  category.getCategoryDesc())).thenReturn(category);
	}

	@Test
	public void whenFindByCode_thenBrandCategoryIsFound() {
		String code = "TST02";

		Category found = categoryDoService.findByCode(Constants.localeENGB,
													  code);

		assertFound(found);
	}
	
	@Test
	public void whenFindByDesc_thenBrandCategoryIsFound() {
		String desc = "test brand category";

		Category found = categoryDoService.findByDesc(Constants.localeENGB,
													  desc);

		assertFound(found);
	}
	
	
    private void assertFound(Category found) {

    	assertNotNull(found);
    	
    	assertThat(found.getCategoryCode())
        .isEqualTo("TST02");
    	
	    assertThat(found.getCategoryDesc())
	    .isEqualTo("test brand category");
    }


}
