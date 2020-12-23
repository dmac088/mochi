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
import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.category.Category;
import io.nzbee.domain.category.ICategoryService;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.product.Product;
import io.nzbee.test.unit.domain.beans.ProductDoBeanFactory;
import io.nzbee.test.unit.domain.beans.CategoryDoBeanFactory;
import io.nzbee.test.unit.domain.beans.CustomerDoBeanFactory;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "tst")
public class UT_BagItemTest {

	@TestConfiguration
	static class BrandCategoryDomainServiceImplUnitTest {
		// the beans that we need to run this unit test
		
		@Bean
		public CustomerDoBeanFactory customerDoBeanFactory() {
			return new CustomerDoBeanFactory();
		}
		
		@Bean
		public ProductDoBeanFactory productDoBeanFactory() {
			return new ProductDoBeanFactory();
		}
		
	}

	@MockBean
	private ICategoryService categoryDoService;
	
	@MockBean
	private ICategoryService productDoService;

	@Autowired
	private CustomerDoBeanFactory customerDoBeanFactory;
	
	@Autowired
	private ProductDoBeanFactory productDoBeanFactory;
	
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.initMocks(this);
		
		Customer c = customerDoBeanFactory.getCustomerDoBean();
		
		Bag bag = new Bag(c);
		
		Product product = productDoBeanFactory.getProductDoBean();
		
		BagItem bagItem = new BagItem(bag, product, 1);

		
		
//		// implement the mocks here 
//		Mockito.when(categoryDoService.findByCode(Constants.localeENGB,
//												  category.getCategoryCode())).thenReturn(category);
//		
//		Mockito.when(categoryDoService.findByDesc(Constants.localeENGB,
//												  category.getCategoryDesc())).thenReturn(category);
	}

	
	@Test
	public void whenEligable_thenB3G33PromotionDiscountIsApplied() {
		String code = "TST02";

		Category found = categoryDoService.findByCode(Constants.localeENGB,
													  code);

		assertFound(found);
	}
	
	
    private void assertFound(final io.nzbee.domain.category.Category found) {

    	assertThat(found.getCategoryCode())
        .isEqualTo("TST02");
    	
	    assertThat(found.getCategoryDesc())
	    .isEqualTo("test brand category");
    }


}
