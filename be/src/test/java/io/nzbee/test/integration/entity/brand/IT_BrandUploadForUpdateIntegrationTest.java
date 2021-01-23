package io.nzbee.test.integration.entity.brand;

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
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.brand.BrandDTO;
import io.nzbee.util.brand.BrandMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@SqlGroup({
		@Sql(scripts = "/database/mochi_schema.sql", config = @SqlConfig(dataSource = "mochiDataSourceOwner", transactionManager = "mochiTransactionManagerOwner", transactionMode = TransactionMode.ISOLATED)),
		@Sql(scripts = "/database/mochi_data.sql", config = @SqlConfig(dataSource = "mochiDataSource", transactionManager = "mochiTransactionManager", transactionMode = TransactionMode.ISOLATED)) })
public class IT_BrandUploadForUpdateIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private BrandMasterService pms;

	@Autowired
	private IBrandService brandService;

	@Before
	public void persistANewBrand() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeBrandMaster(file.getAbsolutePath() + "/data/product/brand/update/brand_master.tsv");
	}

	@Test
	public void whenBrandUploadedForUpdate_thenReturnCorrectlyUpdatedBrand_ENGB() {
		// when
		Optional<BrandDTO> found = brandService.findByCode(Constants.localeENGB, "ENZ01");

		// then
		assertFound_ENGB(found);
	}

	@Test
	public void whenBrandUploadedForUpdate_thenReturnCorrectlyUpdatedBrand_ZHHK() {
		// when
		Optional<BrandDTO> found = brandService.findByCode(Constants.localeZHHK, "ENZ01");

		// then
		assertFound_ZHHK(found);
	}

	private void assertFound_ENGB(Optional<BrandDTO> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getBrandDesc())
		.isEqualTo("Enza Update EN");
		
	}

	private void assertFound_ZHHK(Optional<BrandDTO> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getBrandDesc())
		.isEqualTo("Enza Update HK");
	}

	@After
	public void closeConnection() {
		entityManager.close();
	}
}
