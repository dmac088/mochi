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
import io.nzbee.entity.category.ICategoryService;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.util.category.CategoryMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
@SqlGroup({
		@Sql(scripts = "/database/mochi_schema.sql", config = @SqlConfig(dataSource = "mochiDataSourceOwner", transactionManager = "mochiTransactionManagerOwner", transactionMode = TransactionMode.ISOLATED)),
		@Sql(scripts = "/database/mochi_data.sql", config = @SqlConfig(dataSource = "mochiDataSource", transactionManager = "mochiTransactionManager", transactionMode = TransactionMode.ISOLATED)) })
public class IT_CategoryUploadForCreateIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private CategoryMasterService pms;

	@Autowired
	private ICategoryService categoryService;

	@Before
	public void persistANewCategory() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeCategoryMaster(file.getAbsolutePath() + "/data/product/category/create/category_master.tsv");
	}

	@Test
	public void whenCategoryUploadedForCreate_thenReturnCorrectlyCreatedCategory_ENGB() {
		// when
		Optional<CategoryEntity> found = categoryService.findByCode("TST01");

		// then
		assertFound_ENGB(found);
	}

	@Test
	public void whenCategoryUploadedForCreate_thenReturnCorrectlyCreatedCategory_ZHHK() {
		// when
		Optional<CategoryEntity> found = categoryService.findByCode("TST01");

		// then
		assertFound_ZHHK(found);
	}

	private void assertFound_ENGB(Optional<CategoryEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		CategoryProductEntity cp = (CategoryProductEntity) found.get();
		
		assertThat(cp.getCategoryDescENGB())
		.isEqualTo("Test Category");
		
		assertThat(cp.getCategoryLevel())
		.isEqualTo(1);
		
		assertThat(cp.getCategoryParentCode())
		.isEqualTo("PRM02");
		
	}

	private void assertFound_ZHHK(Optional<CategoryEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		CategoryProductEntity cp = (CategoryProductEntity) found.get();
		
		assertThat(cp.getCategoryDescZHHK())
		.isEqualTo("測試類別");
		
		assertThat(cp.getCategoryLevel())
		.isEqualTo(1);
		
		assertThat(cp.getCategoryParentCode())
		.isEqualTo("PRM02");
	}

	@After
	public void closeConnection() {
		entityManager.close();
	}
}
