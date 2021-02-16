package io.nzbee.test.integration.entity.product.shipping.destination;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.product.shipping.destination.IShippingDestinationService;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationDTO;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationEntity;
import io.nzbee.test.integration.entity.beans.product.shipping.destination.IShippingDestinationEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_ShippingDestinationEntityRepositoryIntegrationTest {

	@TestConfiguration
	static class ShippingDestinationEntityRepositoryIntegrationTest {

	}

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	private IShippingDestinationEntityBeanFactory shippingDestinationEntityBeanFactory;

	@Autowired
	private IShippingDestinationService shippingDestinationService;

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	private static ShippingDestinationEntity shippingDestination = null;

	private static boolean setUpIsDone = false;

	public void persistNewShippingDestination() {

		shippingDestination = shippingDestinationEntityBeanFactory.getBean();

		// persist a new transient test shippingDestination
		shippingDestinationService.save(shippingDestination);
	}

	@Before
	public void persistANewShippingDestination() {
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
		this.persistNewShippingDestination();
		setUpIsDone = true;
	}

	@Test
	@Rollback(false)
	public void whenFindById_thenReturnShippingDestinationEntity() {

		// when
		Optional<ShippingDestinationEntity> found = shippingDestinationService.findById(shippingDestination.getShippingDestinationId());

		// then
		assertFoundEntity(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnShippingDestinationEntity() {

		// when
		Optional<ShippingDestinationEntity> found = shippingDestinationService.findByCode("TST01");

		// then
		assertFoundEntity(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnShippingDestinationDTO() {

		// when
		Optional<ShippingDestinationDTO> found = shippingDestinationService.findByCode(Constants.localeENGB, "TST01");

		// then
		assertFoundDTO(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByDesc_thenReturnShippingDestinationDTO() {

		// when
		Optional<ShippingDestinationDTO> found = shippingDestinationService.findByDesc(Constants.localeENGB, "Test localized shipping destination description");

		// then
		assertFoundDTO(found);
	}

	@Test
	@Rollback(false)
	public void whenFindAllForTestShippingDestination_thenReturnShippingDestinationDTO() {

		Set<String> shippingDestinationCodes = new HashSet<String>();

		shippingDestinationCodes.add("TST01");

		// when
		List<ShippingDestinationDTO> lb = shippingDestinationService.findAll(Constants.localeENGB, new StringCollectionWrapper(shippingDestinationCodes));

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(1);
	}

	private void assertFoundEntity(Optional<ShippingDestinationEntity> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getShippingDestinationCode()).isEqualTo("TST01");
		assertThat(found.get().getAttributes().stream().filter(t -> t.getLclCd().equals(Constants.localeENGB)).findAny()
				.get().getShippingDestinationDesc()).isEqualTo("Test localized shipping destination description");
	}

	private void assertFoundDTO(Optional<ShippingDestinationDTO> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getShippingDestinationCode()).isEqualTo("TST01");
		assertThat(found.get().getShippingDestinationDesc()).isEqualTo("Test localized shipping destination description");

	}

}
