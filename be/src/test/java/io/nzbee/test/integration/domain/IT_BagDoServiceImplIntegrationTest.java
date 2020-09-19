package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.bag.Bag;
import io.nzbee.domain.bag.BagStatus;
import io.nzbee.domain.customer.Customer;
import io.nzbee.domain.customer.ICustomerService;
import io.nzbee.domain.ports.IBagPortService;
import io.nzbee.domain.product.IProductService;
import io.nzbee.test.integration.beans.BagDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
public class IT_BagDoServiceImplIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	private IBagPortService bagService;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private BagDoBeanFactory bagDoBeanFactory;

	@Autowired
	private IProductService productService;

	
	public Bag persistNewBag() {

		Customer c = customerService.findByUsername("dmac088");

		Bag bag = bagDoBeanFactory.getBagDoBean(c);

		bag.addItem(productService.findByCode(Constants.localeENGB, Constants.currencyHKD, "23464789"), 2);
		bag.addItem(productService.findByCode(Constants.localeENGB, Constants.currencyHKD, "12345678"), 3);
		bag.addItem(productService.findByCode(Constants.localeENGB, Constants.currencyHKD, "12345678"), 3);
		
		bagService.save(bag);

		return bag;
	}

	@Before
	public void setUp() {
		this.persistNewBag();
	}

	@Test
	@WithUserDetails(value = "admin")
	public void whenValidCode_thenBagShouldBeFound() {
		Bag found = bagService.findByCode(Constants.localeENGB, Constants.currencyHKD, "dmac088");

		assertFound(found);
	}

	private void assertFound(Bag found) {

		assertThat(found.getBagStatus()).isEqualTo(BagStatus.NEW);

		assertThat(found.getBagItems().size()).isEqualTo(2);

	}
}