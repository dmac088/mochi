package io.nzbee.test.integration.entity.promotion.type;

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
import io.nzbee.entity.promotion.type.IPromotionTypeService;
import io.nzbee.entity.promotion.type.PromotionTypeEntity;
import io.nzbee.test.integration.entity.beans.promotion.type.IPromotionTypeEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
public class IT_PromotionTypeEntityRepositoryIntegrationTest {

	@TestConfiguration
	static class PromotionTypeEntityRepositoryIntegrationTest {

	}

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	private IPromotionTypeEntityBeanFactory promotionTypeEntityBeanFactory;

	@Autowired
	private IPromotionTypeService promotionTypeService;

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	private static PromotionTypeEntity promotionType = null;

	private static boolean setUpIsDone = false;

	public void persistNewPromotionType() {

		promotionType = promotionTypeEntityBeanFactory.getBean();

		// persist a new transient test promotionType
		promotionTypeService.save(promotionType);
	}

	@Before
	public void persistANewPromotionType() {
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
		this.persistNewPromotionType();
		setUpIsDone = true;
	}

	@Test
	@Rollback(false)
	public void whenFindById_thenReturnPromotionTypeEntity() {

		// when
		Optional<PromotionTypeEntity> found = promotionTypeService.findById(promotionType.getPromotionTypeId());

		// then
		assertFoundEntity(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnPromotionTypeEntity() {

		// when
		Optional<PromotionTypeEntity> found = promotionTypeService.findByCode("TST02");

		// then
		assertFoundEntity(found);
	}
     
	private void assertFoundEntity(Optional<PromotionTypeEntity> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getPromotionTypeCode()).isEqualTo("TST01");
		assertThat(found.get().getPromotionTypeDesc()).isEqualTo("test promotion type");
		assertThat(found.get().getPromotionTypeDesc()).isEqualTo("TestPromotionType");
	}


}
