package io.nzbee.test.integration.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.After;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.customer.address.Address;
import io.nzbee.domain.ports.IAddressPortService;
import io.nzbee.test.integration.view.beans.customer.ICustomerDoBeanFactory;
import io.nzbee.test.integration.view.beans.customer.address.CustomerAddressDoBeanFactory;
import io.nzbee.test.integration.view.beans.customer.address.ICustomerAddressDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
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
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	@MockBean
	private ICustomerDoBeanFactory customerDoBeanFactory;
	
	private static boolean setUpIsDone = false;
	
	@Before
	@WithUserDetails(value = "admin")
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
		this.persistNewAddress();
		setUpIsDone = true;
	}
	
	public void persistNewAddress() { 
		Address address = customerAddressDoBeanFactory.getBean();
	    	
		customerAddressService.save(address);
	}
	
	@After
    public void clear() {
        SecurityContextHolder.clearContext();
    }
	
	@Test
	@WithUserDetails(value = "admin")
	@Rollback(false)
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
