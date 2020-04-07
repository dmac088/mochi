package io.nzbee.test.unit.domain;

import static org.assertj.core.api.Assertions.assertThat;

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
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.test.integration.beans.CategoryDoBeanFactory;
import io.nzbee.variables.GeneralVars;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
public class UT_ProductCategoryTest {

	@TestConfiguration
	static class ProductCategoryDomainServiceImplUnitTest {
		// the beans that we need to run this integration test
		@Bean
		public ICategoryService categoryDomainService() {
			return new CategoryServiceImpl();
		}
		
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

		Category category = categoryDoBeanFactory.getProductCategoryDoBean();

		// need to fill more of the properties here
		Mockito.when(categoryDoService.findByCode(GeneralVars.LANGUAGE_ENGLISH, GeneralVars.CURRENCY_HKD,
				category.getCategoryCode())).thenReturn(category);
		
		Mockito.when(categoryDoService.findByDesc(GeneralVars.LANGUAGE_ENGLISH, GeneralVars.CURRENCY_HKD,
				category.getCategoryDesc())).thenReturn(category);
	}

	@Test
	public void whenValidCode_thenProductCategoryShouldBeFound() {
		String code = "TST01";
		String desc = "test product category";

		io.nzbee.domain.category.Category found = categoryDoService.findByCode(	GeneralVars.LANGUAGE_ENGLISH,
																					GeneralVars.CURRENCY_HKD, 
																					code);

		assertThat(found.getCode()).isEqualTo(code);
		assertThat(found.getDesc()).isEqualTo(desc);
	}
	
	@Test
	public void whenValidDesc_thenProductCategoryShouldBeFound() {
		String code = "TST01";
		String desc = "test product category";

		io.nzbee.domain.category.Category found = categoryDoService.findByDesc(	GeneralVars.LANGUAGE_ENGLISH,
																				GeneralVars.CURRENCY_HKD, 
																				desc);

		assertThat(found.getCode()).isEqualTo(code);
		assertThat(found.getDesc()).isEqualTo(desc);
	}


}
