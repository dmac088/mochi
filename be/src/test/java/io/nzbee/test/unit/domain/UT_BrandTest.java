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
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.brand.BrandServiceImpl;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.entity.adapters.PostgresBrandAdapter;
import io.nzbee.test.unit.domain.beans.brand.BrandDoBeanFactory;
import io.nzbee.test.unit.domain.beans.brand.IBrandDoBeanFactory;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "ut")
public class UT_BrandTest {

	@TestConfiguration
	static class ProductBrandDomainServiceImplUnitTest {
		// the beans that we need to run this test
		
		@Bean
		public IBrandDoBeanFactory brandDoBeanFactory() {
			return new BrandDoBeanFactory();
		}
		
		@Bean
		public IBrandPortService brandPortService() {
			return new PostgresBrandAdapter();
		}
		
		@Bean 
		public IBrandService brandService() {
			return new BrandServiceImpl();
		}
		
	}
	
	@Autowired
	private IBrandService brandService;

	@MockBean
	private IBrandPortService brandPortService;

	@Autowired
	private IBrandDoBeanFactory brandDoBeanFactory;
	
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.initMocks(this);

		Brand brand = brandDoBeanFactory.getBean();

		// need to fill more of the properties here
		Mockito.when(brandPortService.findByCode(Constants.localeENGB,
												brand.getBrandCode())).thenReturn(brand);
		
		Mockito.when(brandPortService.findByDesc(Constants.localeENGB,
												brand.getBrandDesc())).thenReturn(brand);
	}

	@Test
	public void whenFindByCode_thenProductBrandIsFound() {
		String code = "TST03";

		Brand found = brandService.findByCode(Constants.localeENGB,
											  code);

		assertFound(found);
	}
	
	@Test
	public void whenFindByDesc_thenProductBrandIsFound() {
		String desc = "test brand";

		Brand found = brandService.findByDesc(Constants.localeENGB,
											  desc);

		assertFound(found);
	}
	
	
    private void assertFound(Brand found) {

    	assertNotNull(found);
    	
    	assertThat(found.getBrandCode())
        .isEqualTo("TST03");
    	
	    assertThat(found.getBrandDesc())
	    .isEqualTo("test brand");
    }


}
