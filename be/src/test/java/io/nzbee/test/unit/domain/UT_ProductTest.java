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
import io.nzbee.domain.product.Product;
import io.nzbee.domain.product.ProductServiceImpl;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.entity.adapters.PostgresBrandAdapter;
import io.nzbee.entity.adapters.PostgresProductAdapter;
import io.nzbee.test.unit.domain.beans.product.ProductDoBeanFactory;
import io.nzbee.test.unit.domain.beans.promotion.IPromotionDoBeanFactory;
import io.nzbee.test.unit.domain.beans.promotion.PromotionDoBeanFactory;
import io.nzbee.test.unit.domain.beans.brand.BrandDoBeanFactory;
import io.nzbee.test.unit.domain.beans.brand.IBrandDoBeanFactory;
import io.nzbee.test.unit.domain.beans.category.CategoryDoBeanFactory;
import io.nzbee.test.unit.domain.beans.category.ICategoryDoBeanFactory;
import io.nzbee.test.unit.domain.beans.department.DepartmentDoBeanFactory;
import io.nzbee.test.unit.domain.beans.department.IDepartmentDoBeanFactory;
import io.nzbee.test.unit.domain.beans.product.IProductDoBeanFactory;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "ut")
public class UT_ProductTest {

	@TestConfiguration
	static class ProductProductDomainServiceImplUnitTest {
		// the beans that we need to run this test
		
		@Bean
		public IProductDoBeanFactory productDoBeanFactory() {
			return new ProductDoBeanFactory();
		}
		
		@Bean
		public IBrandDoBeanFactory brandDoBeanFactory() {
			return new BrandDoBeanFactory();
		}
		
		@Bean
		public IDepartmentDoBeanFactory departmentDoBeanFactory() {
			return new DepartmentDoBeanFactory();
		}
		
		@Bean
		public ICategoryDoBeanFactory categoryDoBeanFactory() {
			return new CategoryDoBeanFactory();
		}
		
		@Bean
		public IPromotionDoBeanFactory promotionDoBeanFactory() {
			return new PromotionDoBeanFactory();
		}
		
		@Bean
		public IProductPortService productPortService() {
			return new PostgresProductAdapter();
		}
		
		@Bean
		public IBrandPortService brandPortService() {
			return new PostgresBrandAdapter();
		}
		
		@Bean 
		public IProductService productService() {
			return new ProductServiceImpl();
		}
		
	}
	
	@Autowired
	private IProductService productService;

	@MockBean
	private IProductPortService productPortService;
	
	@MockBean
	private IBrandPortService brandPortService;
	
	@Autowired
	private IBrandDoBeanFactory brandDoBeanFactory;

	@Autowired
	private IProductDoBeanFactory productDoBeanFactory;
	
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.initMocks(this);

		Product product = productDoBeanFactory.getBean();
		Brand 	brand 	= brandDoBeanFactory.getBean();

		// need to fill more of the properties here
		Mockito.when(productPortService.findByCode(	Constants.localeENGB,
													Constants.currencyHKD,
													product.getProductUPC())).thenReturn(product);
		
		Mockito.when(productPortService.findByDesc(	Constants.localeENGB,
													Constants.currencyHKD,
													product.getProductDesc())).thenReturn(product);
		
		Mockito.when(brandPortService.findByCode(	Constants.localeENGB,
													brand.getBrandCode())).thenReturn(brand);
		
		Mockito.when(brandPortService.findByDesc(	Constants.localeENGB,
													brand.getBrandDesc())).thenReturn(brand);
	}

	@Test
	public void whenFindByCode_thenProductProductIsFound() {
		String code = "3254354673";

		Product found = productService.findByCode(Constants.localeENGB,
											  	code);

		assertFound(found);
	}
	
	@Test
	public void whenFindByDesc_thenProductProductIsFound() {
		String desc = "Test Product Description";

		Product found = productService.findByDesc(Constants.localeENGB,
											  desc);

		assertFound(found);
	}
	
	
    private void assertFound(Product found) {

    	assertNotNull(found);
    	
    	assertThat(found.getProductUPC())
        .isEqualTo("3254354673");
    	
	    assertThat(found.getProductDesc())
	    .isEqualTo("Test Product Description");
    }


}
