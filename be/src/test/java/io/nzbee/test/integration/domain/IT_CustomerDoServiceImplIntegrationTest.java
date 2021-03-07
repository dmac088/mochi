package io.nzbee.test.integration.domain;

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
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.ports.ICustomerPortService;
import io.nzbee.test.integration.domain.beans.customer.CustomerDoBeanFactory;
import io.nzbee.test.integration.domain.beans.customer.ICustomerDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
public class IT_CustomerDoServiceImplIntegrationTest {

	@TestConfiguration
	static class CustomerDoServiceImplIntegrationTest_Configuration {
		// the beans that we need to run this test
		@Bean
		public ICustomerDoBeanFactory customerDoBeanFactory() {
			return new CustomerDoBeanFactory();
		}
		
	}
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
	private ICustomerPortService customerService;

	@Autowired
	private ICustomerDoBeanFactory customerDoBeanFactory;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

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
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/security_schema.sql"));
			ScriptUtils.executeSqlScript(con, new ClassPathResource("/database/security_data.sql"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.persistNewCustomer();
		setUpIsDone = true;
	}
	
	public void persistNewCustomer() { 
		Customer customer = customerDoBeanFactory.getBean();
	    	
		customerService.save(customer);
	}
	
	@After
    public void clear() {
        SecurityContextHolder.clearContext();
    }
	
	@Test
	@Rollback(false)
	@WithUserDetails(value = "admin")
	public void whenFindCustomerByUsername_thenReturnCustomer() {
		
		// when
		Customer found = customerService.findByUsername("tst088");

		// then
		assertFound(found);
	}

	private void assertFound(final Customer found) {

		assertNotNull(found);
		
		assertThat(found.getGivenName())
	    .isEqualTo("test given name");
		
		assertThat(found.getFamilyName())
	    .isEqualTo("test family name");
		
		assertThat(found.getCustomerID())
	    .isEqualTo("123456789");
		
		assertThat(found.getUserName())
	    .isEqualTo("tst088");
		
	}
	
}
