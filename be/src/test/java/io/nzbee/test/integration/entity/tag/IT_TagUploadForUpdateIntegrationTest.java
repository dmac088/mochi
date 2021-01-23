package io.nzbee.test.integration.entity.tag;

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
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagDTO;
import io.nzbee.util.tag.TagMasterService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
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
    	this.uploadTags();
        setUpIsDone = true;
	}

	public void uploadTags() {
		String path = "src/test/resources";
		File file = new File(path);

		pms.writeTagMaster(file.getAbsolutePath() + "/data/product/tag/update/tag_master.tsv");
	}

	@Test
	@Rollback(false)
	public void whenTagUploadedForUpdate_thenReturnCorrectlyUpdatedTag_ENGB() {
		// when
		Optional<TagDTO> found = tagService.findByCode(Constants.localeENGB, "GFR01");

		// then
		assertFound_ENGB(found);
	}

	@Test
	@Rollback(false)
	public void whenTagUploadedForUpdate_thenReturnCorrectlyUpdatedTag_ZHHK() {
		// when
		Optional<TagDTO> found = tagService.findByCode(Constants.localeZHHK, "GFR01");

		// then
		assertFound_ZHHK(found);
	}

	private void assertFound_ENGB(Optional<TagDTO> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getTagDesc())
		.isEqualTo("Gluten Free Test");
		
	}

	private void assertFound_ZHHK(Optional<TagDTO> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());
		
		assertThat(found.get().getTagDesc())
		.isEqualTo("無麩質測試");
	}

	@After
	public void closeConnection() {
		entityManager.close();
	}
}
