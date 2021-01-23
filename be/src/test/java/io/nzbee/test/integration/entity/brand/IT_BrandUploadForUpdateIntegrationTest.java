package io.nzbee.test.integration.entity.brand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import javax.persistence.EntityManager;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.brand.IBrandService;
import io.nzbee.entity.brand.BrandDTO;
import io.nzbee.util.brand.BrandMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
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

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	private static boolean setUpIsDone = false;
	
	@Before
	public void setUp() {
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
		this.persistANewBrand();
		setUpIsDone = true;
	}
	
	
	public void persistANewBrand() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeBrandMaster(file.getAbsolutePath() + "/data/product/brand/update/brand_master.tsv");
	}

	@Test
	@Rollback(false)
	public void whenBrandUploadedForUpdate_thenReturnCorrectlyUpdatedBrand_ENGB() {
		// when
		Optional<BrandDTO> found = brandService.findByCode(Constants.localeENGB, "ENZ01");

		// then
		assertFound_ENGB(found);
	}

	@Test
	@Rollback(false)
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
