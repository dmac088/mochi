package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagItem;
import io.nzbee.domain.bag.BagServiceImpl;
import io.nzbee.domain.bag.IBagService;
import io.nzbee.domain.brand.BrandServiceImpl;
import io.nzbee.domain.brand.IBrandService;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.customer.CustomerServiceImpl;
import io.nzbee.domain.customer.ICustomerService;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.domain.product.IProductService;
import io.nzbee.domain.product.ProductServiceImpl;
import io.nzbee.dto.bag.item.BagItemDTOMapperImpl;
import io.nzbee.dto.bag.item.IBagItemDTOMapper;
import io.nzbee.test.integration.domain.beans.bag.IBagDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
public class IT_BagDoServiceImplIntegrationTest {

	@TestConfiguration
	static class BagDoServiceImplIntegrationTest_Configuration {
		// the beans that we need to run this test
		
		@Bean
		public IBagService bagService() {
			return new BagServiceImpl();
		}
		
		@Bean
		public IBrandService brandService() {
			return new BrandServiceImpl();
		}
		
		@Bean
		public IProductService productService() {
			return new ProductServiceImpl();
		}
		
		@Bean
		public ICustomerService customerService() {
			return new CustomerServiceImpl();
		}
		
		@Bean 
		public IBagItemDTOMapper dtoMapper() {
			return new BagItemDTOMapperImpl();
		}
		
	}
	
	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	private IBagPortService bagService;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IBagDoBeanFactory bagDoBeanFactory;

	@Autowired
	private IProductService productService;

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	private static boolean setUpIsDone = false;
	
	@Before
	public void setUp() {
		if (setUpIsDone) {
			return;
		}
		try (Connection con = database.getConnection()) {
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_schema.sql"));
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/mochi_data.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.persistNewBag();
		setUpIsDone = true;
	}
	
	public void persistNewBag() {

		Customer c = customerService.findByUsername("bob@bob");

		Bag bag = bagDoBeanFactory.getBean(c);
		
		bag.addItem(productService.findByCode(Constants.localeENGB, Constants.currencyHKD, "23464789"), 2);
		bag.addItem(productService.findByCode(Constants.localeENGB, Constants.currencyHKD, "12345678"), 3);
		bag.addItem(productService.findByCode(Constants.localeENGB, Constants.currencyHKD, "12345678"), 3);
		
		bag.addItem(new BagItem(bag, productService.findByCode(Constants.localeENGB, Constants.currencyHKD, "17235347"), 1));
		
		bagService.save(bag);
	}

	@Test
	@Rollback(false)
	@WithUserDetails(value = "admin")
	public void whenValidCode_thenBagShouldBeFound() {
		Bag found = bagService.findByCode(Constants.localeENGB, Constants.currencyHKD, "bob@bob");

		assertFound(found);
	}

	private void assertFound(Bag found) {
		
		assertNotNull(found);
		
		assertTrue(!found.getBagItems().isEmpty());

		assertThat(found.getBagItems().size()).isEqualTo(3);
		
		assertThat(found.getTotalQuantity()).isEqualTo(9);

	}
}
