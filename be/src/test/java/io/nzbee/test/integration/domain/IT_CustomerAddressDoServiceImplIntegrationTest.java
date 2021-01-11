package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.customer.address.Address;
import io.nzbee.domain.ports.IAddressPortService;
import io.nzbee.test.integration.domain.beans.customer.ICustomerDoBeanFactory;
import io.nzbee.test.integration.domain.beans.customer.address.CustomerAddressDoBeanFactory;
import io.nzbee.test.integration.domain.beans.customer.address.ICustomerAddressDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
@SqlGroup({
	@Sql(scripts = "/database/mochi_schema.sql",
			config = @SqlConfig(dataSource = "mochiDataSourceOwner", 
			transactionManager = "mochiTransactionManagerOwner",
			transactionMode = TransactionMode.ISOLATED)), 
	@Sql(scripts = "/database/mochi_data.sql",
			config = @SqlConfig(dataSource = "mochiDataSource", 
			transactionManager = "mochiTransactionManager",
			transactionMode = TransactionMode.ISOLATED))
})
public class IT_CustomerAddressDoServiceImplIntegrationTest {

	@TestConfiguration
	static class CustomerAddressDoServiceImplIntegrationTest_Configuration {
		// the beans that we need to run this test
		@Bean
		public ICustomerAddressDoBeanFactory customerAddressDoBeanFactory() {
			return new CustomerAddressDoBeanFactory();
		}
		
	}
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	private IAddressPortService customerAddressService;

	@Autowired
	private ICustomerAddressDoBeanFactory customerAddressDoBeanFactory;
	
	@MockBean
	private ICustomerDoBeanFactory customerDoBeanFactory;
	
	@Before
	@WithUserDetails(value = "admin")
	public void setUp() { 
		Address address = customerAddressDoBeanFactory.getBean();
	    	
		customerAddressService.save(address);
	}
	
	@After
    public void clear() {
        SecurityContextHolder.clearContext();
    }
	
	@Test
	@WithUserDetails(value = "admin")
	public void whenFindCustomerAddressByUsername_thenReturnCustomerAddress() {
		
		// when
		Address found = customerAddressService.findByUsernameAndType("bob@bob", "BIL01");

		// then
		assertFound(found);
	}

	private void assertFound(Address found) {

		assertNotNull(found);
		
		assertThat(found.getAddressLine1())
	    .isEqualTo("Test Line 1");
		
		assertThat(found.getAddressLine2())
	    .isEqualTo("Test Line 2");
		
		assertThat(found.getAddressLine3())
	    .isEqualTo("Test Line 3");
		
		assertThat(found.getCountry())
	    .isEqualTo("Test Country");
		
		assertThat(found.getPostCode())
	    .isEqualTo("Test PC");
		
		assertThat(found.getAddressTypeCode())
	    .isEqualTo("BIL01");
		
		assertThat(found.getAddressTypeDesc())
	    .isEqualTo("Billing Address");
		
		assertThat(found.getCustomer().getUserName())
	    .isEqualTo("bob@bob");
		
	}
	
}
