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
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.Tag;
import io.nzbee.util.tag.TagMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
@SqlGroup({
		@Sql(scripts = "/database/mochi_schema.sql", config = @SqlConfig(dataSource = "mochiDataSourceOwner", transactionManager = "mochiTransactionManagerOwner", transactionMode = TransactionMode.ISOLATED)),
		@Sql(scripts = "/database/mochi_data.sql", config = @SqlConfig(dataSource = "mochiDataSource", transactionManager = "mochiTransactionManager", transactionMode = TransactionMode.ISOLATED)) })
public class IT_TagUploadForUpdateIntegrationTest {

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private TagMasterService pms;

	@Autowired
	private ITagService tagService;

	@Before
	public void persistANewTag() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeTagMaster(file.getAbsolutePath() + "/data/product/tag/update/tag_master.tsv");
	}

	@Test
	public void whenTagUploadedForUpdate_thenReturnCorrectlyUpdatedTag_ENGB() {
		// when
		Optional<Tag> found = tagService.findByCode(Constants.localeENGB, "GFR01");

		// then
		assertFound_ENGB(found);
	}

	@Test
	public void whenTagUploadedForUpdate_thenReturnCorrectlyUpdatedTag_ZHHK() {
		// when
		Optional<Tag> found = tagService.findByCode(Constants.localeZHHK, "GFR01");

		// then
		assertFound_ZHHK(found);
	}

	private void assertFound_ENGB(Optional<Tag> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getTagAttribute().getTagDesc())
		.isEqualTo("Gluten Free Test");
		
	}

	private void assertFound_ZHHK(Optional<Tag> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getTagAttribute().getTagDesc())
		.isEqualTo("無麩質測試");
	}

	@After
	public void closeConnection() {
		entityManager.close();
	}
}