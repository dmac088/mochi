package io.nzbee.test.unit.domain;

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
import io.nzbee.domain.category.CategoryServiceImpl;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.test.entity.beans.CategoryDtoBeanFactory;
import io.nzbee.variables.GeneralVars;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
public class UT_ProductCategoryTest {

	@TestConfiguration
	static class ProductCategoryDomainServiceImplUnitTest {
		// the beans that we need to run this integration test
		@Bean(value = "categoryDomainService")
		public ICategoryService categoryDomainService() {
			return new CategoryServiceImpl();
		}

		@Bean(value = "categoryDtoService")
		public io.nzbee.dto.category.ICategoryService categoryDtoService() {
			return new io.nzbee.dto.category.CategoryServiceImpl();
		}

		@Bean(value = "categoryDtoBeanFactory")
		public CategoryDtoBeanFactory categoryFactoryBean() {
			return new CategoryDtoBeanFactory();
		}
	}

	@Autowired
	private ICategoryService categoryDomainService;

	@Autowired
	private CategoryDtoBeanFactory categoryDtoBeanFactory;

	@MockBean
	private io.nzbee.dto.category.ICategoryService categoryDtoService;

	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.initMocks(this);

		io.nzbee.dto.category.Category category = categoryDtoBeanFactory.getProductCategoryDtoBean();

		// need to fill more of the properties here
		Mockito.when(categoryDtoService.findByCode(GeneralVars.LANGUAGE_ENGLISH, GeneralVars.CURRENCY_HKD,
				category.getCategoryCode())).thenReturn(Optional.ofNullable(category));
		
		Mockito.when(categoryDtoService.findByDesc(GeneralVars.LANGUAGE_ENGLISH, GeneralVars.CURRENCY_HKD,
				category.getCategoryDesc())).thenReturn(Optional.ofNullable(category));
	}

	@Test
	public void whenValidCode_thenProductCategoryShouldBeFound() {
		String code = "TST02";
		String desc = "test product category";

		io.nzbee.domain.category.Category found = categoryDomainService.findByCode(	GeneralVars.LANGUAGE_ENGLISH,
																					GeneralVars.CURRENCY_HKD, 
																					code);

		assertThat(found.getCode()).isEqualTo(code);
		assertThat(found.getDesc()).isEqualTo(desc);
	}
	
	@Test
	public void whenValidDesc_thenProductCategoryShouldBeFound() {
		String code = "TST02";
		String desc = "test product category";

		io.nzbee.domain.category.Category found = categoryDomainService.findByDesc(	GeneralVars.LANGUAGE_ENGLISH,
																					GeneralVars.CURRENCY_HKD, 
																					desc);

		assertThat(found.getCode()).isEqualTo(code);
		assertThat(found.getDesc()).isEqualTo(desc);
	}


}
