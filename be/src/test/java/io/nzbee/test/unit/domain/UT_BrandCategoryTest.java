package io.nzbee.test.unit.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.test.integration.beans.CategoryDoBeanFactory;
import io.nzbee.variables.GeneralVars;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
public class UT_BrandCategoryTest {

	@TestConfiguration
	static class BrandCategoryDomainServiceImplUnitTest {
		// the beans that we need to run this integration test
		@Bean(value = "categoryDomainService")
		public ICategoryService categoryDomainService() {
			return new CategoryServiceImpl();
		}
	}

	@Autowired
	private ICategoryService categoryDoService;


	@Autowired
	private CategoryDoBeanFactory categoryDoBeanFactory;
	
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.initMocks(this);

		Category category = categoryDoBeanFactory.getBrandCategoryDoBean();

		// need to fill more of the properties here
		Mockito.when(categoryDoService.findByCode(GeneralVars.LANGUAGE_ENGLISH, GeneralVars.CURRENCY_HKD,
				category.getCategoryCode())).thenReturn(category);
		
		Mockito.when(categoryDoService.findByDesc(GeneralVars.LANGUAGE_ENGLISH, GeneralVars.CURRENCY_HKD,
				category.getCategoryDesc())).thenReturn(category);
	}

	@Test
	public void whenValidCode_thenBrandCategoryShouldBeFound() {
		String code = "TST01";

		io.nzbee.domain.category.Category found = categoryDoService.findByCode(GeneralVars.LANGUAGE_ENGLISH,
																				   GeneralVars.CURRENCY_HKD, 
																				   code);

		assertFound(found);
	}
	
	@Test
	public void whenValidDesc_thenBrandCategoryShouldBeFound() {
		String desc = "test brand category";

		io.nzbee.domain.category.Category found = categoryDoService.findByDesc(	GeneralVars.LANGUAGE_ENGLISH,
																					GeneralVars.CURRENCY_HKD, 
																					desc);

		assertFound(found);
	}
	
    private void assertFound(final io.nzbee.domain.category.Category found) {

    	assertThat(found.getCode())
        .isEqualTo("TST01");
    	
	    assertThat(found.getLevel())
	    .isEqualTo(new Long(2));
	    
	    assertThat(found.getDesc())
	    .isEqualTo("test brand category");
    }


}
