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
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.department.Department;
import io.nzbee.domain.ports.IBrandPortService;
import io.nzbee.domain.ports.ICategoryPortService;
import io.nzbee.domain.ports.IDepartmentPortService;
import io.nzbee.domain.ports.IProductPortService;
import io.nzbee.domain.ports.IPromotionPortService;
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
		
	}
	
	@Autowired
	private IProductService productService;

	@MockBean
	private IProductPortService productPortService;
	
	@MockBean
	private IBrandPortService brandPortService;
	
	@MockBean
	private IDepartmentPortService departmentPortService;
	
	@MockBean
	private ICategoryPortService categoryPortService;
	
	@MockBean
	private IPromotionPortService promotionPortService;
	
	@Autowired
	private IBrandDoBeanFactory brandDoBeanFactory;
	
	@Autowired
	private IDepartmentDoBeanFactory departmentDoBeanFactory;
	
	@Autowired
	private ICategoryDoBeanFactory categoryDoBeanFactory;
	
	@Autowired
	private IPromotionDoBeanFactory promotionDoBeanFactory;

	@Autowired
	private IProductDoBeanFactory productDoBeanFactory;
	
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.initMocks(this);

		Product 	product 		= productDoBeanFactory.getBean();
		Brand 		brand 			= brandDoBeanFactory.getBean();
		Department 	department 		= departmentDoBeanFactory.getBean();
		Category 	category 		= categoryDoBeanFactory.getBean();
		Promotion 	promotion 		= promotionDoBeanFactory.getBean();
		

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
		
		Mockito.when(departmentPortService.findByCode(	Constants.localeENGB,
														brand.getBrandCode())).thenReturn(department);

		Mockito.when(departmentPortService.findByDesc(	Constants.localeENGB,
														brand.getBrandDesc())).thenReturn(department);
		
		Mockito.when(categoryPortService.findByCode(	Constants.localeENGB,
														category.getCategoryCode())).thenReturn(category);

		Mockito.when(categoryPortService.findByDesc(	Constants.localeENGB,
														category.getCategoryDesc())).thenReturn(category);
		
		Mockito.when(promotionPortService.findByCode(	Constants.localeENGB,
														promotion.getPromotionCode())).thenReturn(promotion);

//		Mockito.when(promotionPortService.findByDesc(	Constants.localeENGB,
//														promotion.getPromotionDesc())).thenReturn(promotion);
	}

	@Test
	public void whenFindByCode_thenProductProductIsFound() {
		String code = "3254354673";

		Product found = productService.findByCode(Constants.localeENGB,
												  Constants.currencyHKD,
											  	  code);

		assertFound(found);
	}
	
	@Test
	public void whenFindByDesc_thenProductProductIsFound() {
		String desc = "Test Product Description";

		Product found = productService.findByDesc(Constants.localeENGB,
												  Constants.currencyHKD,
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
