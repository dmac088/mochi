package io.nzbee.test.unit.domain;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.bag.BagItemServiceImpl;
import io.nzbee.domain.bag.IBagItemService;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.IBagItemPortService;
import io.nzbee.domain.product.Product;
import io.nzbee.domain.promotion.Promotion;
import io.nzbee.domain.promotion.PromotionType;
import io.nzbee.test.unit.domain.beans.ProductDoBeanFactory;
import io.nzbee.test.unit.domain.beans.BrandDoBeanFactory;
import io.nzbee.test.unit.domain.beans.CustomerDoBeanFactory;
import io.nzbee.test.unit.domain.beans.DepartmentDoBeanFactory;
import io.nzbee.test.unit.domain.beans.CategoryDoBeanFactory;
import io.nzbee.test.unit.domain.beans.PromotionDoBeanFactory;

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
		
		@Bean
		public IBagItemService bagItemService() {
			return new BagItemServiceImpl();
		}
		
		@Bean
		public BrandDoBeanFactory brandDoBeanFactory() {
			return new BrandDoBeanFactory();
		}
		
		@Bean
		public DepartmentDoBeanFactory departmentDoBeanFactory() {
			return new DepartmentDoBeanFactory();
		}
		
		@Bean
		public CategoryDoBeanFactory CategoryDoBeanFactory() {
			return new CategoryDoBeanFactory();
		}
		
		@Bean
		public PromotionDoBeanFactory PromotionDoBeanFactory() {
			return new PromotionDoBeanFactory();
		}
		
		@Bean
		public IBagItemService BagItemService() {
			return new BagItemServiceImpl();
		}
		
		@Bean 
		public KieServices kieServices() {
			return KieServices.Factory.get();
		}
		
		@Bean 
		public KieContainer bagItemConfiguration() {
			KieServices kieServices = KieServices.Factory.get();
			 
	        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
	        kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
	        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
	        kieBuilder.buildAll();
	        KieModule kieModule = kieBuilder.getKieModule();
	 
	        return kieServices.newKieContainer(kieModule.getReleaseId());
		}
		
	}

	private static final String drlFile = "rules/bagItemRules.drl";
	
	@Autowired
	private CustomerDoBeanFactory customerDoBeanFactory;
	
	@Autowired
	private ProductDoBeanFactory productDoBeanFactory;
	
	@Autowired
    private IBagItemService bagItemService;
    
	@MockBean
	private IBagItemPortService bagItemPortService;
	
    private BagItem bagItem = null;
	
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.initMocks(this);
		
		Customer c = customerDoBeanFactory.getCustomerDoBean();
		
		Bag bag = new Bag(c);
		
		Product product = productDoBeanFactory.getProductDoBean();
		
		bagItem = new BagItem(bag, product, 3);
		
		Promotion b3g33 = new Promotion("B3G33", 
				 						"Buy 3 Get 33% off",
										LocalDateTime.of(2020, Month.JANUARY, 8, 0,0,0),
										LocalDateTime.of(2021, Month.JANUARY, 8, 0,0,0),
										new PromotionType("BNGNPCT", "Buy N Get X Percent Off"));
		
		bagItem.getProduct().addPromotion(b3g33);
		
	
	}

	
	@Test
	public void whenEligable_thenB3G33PromotionDiscountIsApplied() {
		bagItemService.checkAllBagItemRules(bagItem);

		assertDiscountsApplied(bagItem);
	}
	
	
    private void assertDiscountsApplied(BagItem found) {

    	assertThat(found.getDiscounts().size())
        .isEqualTo(1);
    	
    	assertThat(found.getBagItemDiscount())
    	.isEqualTo(new Double(71.0));
    	
    }


}
