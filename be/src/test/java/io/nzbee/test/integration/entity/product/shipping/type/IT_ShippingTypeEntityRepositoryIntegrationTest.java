package io.nzbee.test.integration.entity.product.shipping.type;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
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
import io.nzbee.entity.product.shipping.type.IShippingTypeService;
import io.nzbee.entity.product.shipping.type.ShippingTypeDTO;
import io.nzbee.entity.product.shipping.type.ShippingTypeEntity;
import io.nzbee.test.integration.entity.beans.product.shipping.type.IShippingTypeEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_ShippingTypeEntityRepositoryIntegrationTest {

	@TestConfiguration
	static class ShippingTypeEntityRepositoryIntegrationTest {

	}

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	private IShippingTypeEntityBeanFactory shippingTypeEntityBeanFactory;

	@Autowired
	private IShippingTypeService shippingTypeService;

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	private static ShippingTypeEntity shippingType = null;

	private static boolean setUpIsDone = false;

	public void persistNewShippingType() {
  
		shippingType = shippingTypeEntityBeanFactory.getBean();

		// persist a new transient test shippingType
		shippingTypeService.save(shippingType);
	}

	@Before	
	public void persistANewShippingType() {
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
		this.persistNewShippingType();
		setUpIsDone = true;
	}

	@Test
	@Rollback(false)
	public void whenFindById_thenReturnShippingTypeEntity() {

		// when
		Optional<ShippingTypeEntity> found = shippingTypeService.findById(shippingType.getShippingTypeId());

		// then
		assertFoundEntity(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnShippingTypeEntity() {

		// when
		Optional<ShippingTypeEntity> found = shippingTypeService.findByCode("TST01");

		// then
		assertFoundEntity(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnShippingTypeDTO() {

		// when
		Optional<ShippingTypeDTO> found = shippingTypeService.findByCode(Constants.localeENGB, "TST01");

		// then
		assertFoundDTO(found);
	}

	private void assertFoundEntity(Optional<ShippingTypeEntity> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getShippingTypeCode()).isEqualTo("TST01");
		assertThat(found.get().getAttributes().stream().filter(t -> t.getLclCd().equals(Constants.localeENGB)).findAny()
				.get().getShippingTypeDesc()).isEqualTo("Test localized shipping type description");
	}

	private void assertFoundDTO(Optional<ShippingTypeDTO> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getShippingTypeCode()).isEqualTo("TST01");
		assertThat(found.get().getShippingTypeDesc()).isEqualTo("Test localized shipping type description");

	}

}
