package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.entity.promotion.mechanic.IPromotionMechanicService;
import io.nzbee.entity.promotion.mechanic.PromotionMechanicEntity;
import io.nzbee.util.promotion.PromotionMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
@SqlGroup({
		@Sql(scripts = "/database/mochi_schema.sql", config = @SqlConfig(dataSource = "mochiDataSourceOwner", transactionManager = "mochiTransactionManagerOwner", transactionMode = TransactionMode.ISOLATED)),
		@Sql(scripts = "/database/mochi_data.sql", config = @SqlConfig(dataSource = "mochiDataSource", transactionManager = "mochiTransactionManager", transactionMode = TransactionMode.ISOLATED)) })
public class IT_PromotionMechanicUploadForCreateIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private PromotionMasterService pms;

	@Autowired
	private IPromotionMechanicService promotionMechanicService;

	@Before
	public void persistANewPromotion() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writePromotionMaster(file.getAbsolutePath() + "/data/promotion/mechanic/create/promotion_mechanics.tsv");
	}

	@Test
	public void whenPromotionUploadedForCreate_thenReturnCorrectlyCreatedPromotion() {
		// when
		Optional<PromotionMechanicEntity> found = promotionMechanicService.findByCode("BNGNPCT");

		// then
		assertFound(found);
	}

	private void assertFound(Optional<PromotionMechanicEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		PromotionMechanicEntity cp = (PromotionMechanicEntity) found.get();
		
		assertThat(cp.getPromotionMechanicDesc())
		.isEqualTo("Buy N Get X Percent Off");
		
	}



	@After
	public void closeConnection() {
		entityManager.close();
	}
}
