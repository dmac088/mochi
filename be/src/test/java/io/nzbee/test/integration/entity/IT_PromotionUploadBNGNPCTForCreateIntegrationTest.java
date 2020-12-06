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
import io.nzbee.Constants;
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.entity.promotion.PromotionBNGNPCTEntity;
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.util.promotion.bngnpct.PromotionBNGNPCTMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
@SqlGroup({
		@Sql(scripts = "/database/mochi_schema.sql", config = @SqlConfig(dataSource = "mochiDataSourceOwner", transactionManager = "mochiTransactionManagerOwner", transactionMode = TransactionMode.ISOLATED)),
		@Sql(scripts = "/database/mochi_data.sql", config = @SqlConfig(dataSource = "mochiDataSource", transactionManager = "mochiTransactionManager", transactionMode = TransactionMode.ISOLATED)) })
public class IT_PromotionUploadBNGNPCTForCreateIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private PromotionBNGNPCTMasterService pms;

	@Autowired
	private IPromotionService promotionService;

	@Before
	public void persistANewPromotion() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writePromotionMaster(file.getAbsolutePath() + "/data/promotion/create/promotion_bngnpct.tsv");
	}

	@Test
	public void whenPromotionUploadedForCreate_thenReturnCorrectlyCreatedPromotion_ENGB() {
		// when
		Optional<PromotionEntity> found = promotionService.findByCode("B2G50");

		// then
		assertFound_ENGB(found);
	}

	@Test
	public void whenPromotionUploadedForCreate_thenReturnCorrectlyCreatedPromotion_ZHHK() {
		// when
		Optional<PromotionEntity> found = promotionService.findByCode("B3G33");

		// then
		assertFound_ZHHK(found);
	}

	private void assertFound_ENGB(Optional<PromotionEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		PromotionEntity cp = (PromotionEntity) found.get();
		
		assertThat(cp.getPromotionCode())
		.isEqualTo("B2G50");
		
		assertThat(cp.getAttributes().size()).isEqualTo(2);
		
		assertThat(cp.getAttributes().stream().filter(a -> a.getLocale().equals(Constants.localeENGB)).findAny().get().getPromotionDesc())
		.isEqualTo("Buy 2 get 50% off");
		
		assertThat(((PromotionBNGNPCTEntity) cp).getBuyQty()).isEqualTo(2);
		
		assertThat(((PromotionBNGNPCTEntity) cp).getPctDisc()).isEqualTo(0.5);
		
	}

	private void assertFound_ZHHK(Optional<PromotionEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		PromotionEntity cp = (PromotionEntity) found.get();
		
		assertThat(cp.getPromotionCode())
		.isEqualTo("B3G33");
		
		assertThat(cp.getAttributes().size()).isEqualTo(2);
		
		assertThat(cp.getAttributes().stream().filter(a -> a.getLocale().equals(Constants.localeZHHK)).findAny().get().getPromotionDesc())
		.isEqualTo("買三件可享33％折扣");
		
		assertThat(((PromotionBNGNPCTEntity) cp).getBuyQty()).isEqualTo(3);
		
		assertThat(((PromotionBNGNPCTEntity) cp).getPctDisc()).isEqualTo(0.33);
		
	}

	@After
	public void closeConnection() {
		entityManager.close();
	}
}