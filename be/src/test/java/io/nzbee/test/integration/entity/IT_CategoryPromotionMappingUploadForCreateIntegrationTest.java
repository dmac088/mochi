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
import io.nzbee.entity.promotion.IPromotionService;
import io.nzbee.entity.promotion.PromotionEntity;
import io.nzbee.util.promotion.category.CategoryPromotionMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@SqlGroup({
		@Sql(scripts = "/database/mochi_schema.sql", config = @SqlConfig(dataSource = "mochiDataSourceOwner", transactionManager = "mochiTransactionManagerOwner", transactionMode = TransactionMode.ISOLATED)),
		@Sql(scripts = "/database/mochi_data.sql", config = @SqlConfig(dataSource = "mochiDataSource", transactionManager = "mochiTransactionManager", transactionMode = TransactionMode.ISOLATED)) })
public class IT_CategoryPromotionMappingUploadForCreateIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private CategoryPromotionMasterService pms;

	@Autowired
	private IPromotionService promotionService;

	@Before
	public void persistANewPromotion() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeCategoryPromotionMaster(file.getAbsolutePath() + "/data/promotion/mapping/category/category_promotion_mapping.tsv");
	}

	@Test
	public void whenPromotionUploadedForCreate_thenReturnCorrectlyCreatedPromotion() {
		// when
		Optional<PromotionEntity> found = promotionService.findByCode("B3G33");

		// then
		assertFound(found);
	}

	private void assertFound(Optional<PromotionEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		PromotionEntity cp = (PromotionEntity) found.get();
		
		assertThat(cp.getCategories().size())
		.isEqualTo(2);
		
		assertTrue(cp.getCategories().stream().filter(p -> p.getCategoryCode().equals("CIT01")).findAny().isPresent());
		
		assertTrue(cp.getCategories().stream().filter(p -> p.getCategoryCode().equals("TRO01")).findAny().isPresent());
		
	}


	@After
	public void closeConnection() {
		entityManager.close();
	}
}
