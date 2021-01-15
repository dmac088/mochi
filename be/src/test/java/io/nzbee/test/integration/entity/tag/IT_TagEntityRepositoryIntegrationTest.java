package io.nzbee.test.integration.entity.tag;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.entity.StringCollectionWrapper;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.TagDTO;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.test.integration.entity.beans.tag.ITagEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IT_TagEntityRepositoryIntegrationTest {

	@TestConfiguration
	static class TagEntityRepositoryIntegrationTest {

	}

	@MockBean
	private JavaMailSender mailSender;

	@Autowired
	private ITagEntityBeanFactory tagEntityBeanFactory;

	@Autowired
	private ITagService tagService;

	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;

	private static TagEntity tag = null;

	private static boolean setUpIsDone = false;

	public void persistNewTag() {

		tag = tagEntityBeanFactory.getBean();

		// persist a new transient test tag
		tagService.save(tag);
	}

	@Before
	public void persistANewTag() {
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
		this.persistNewTag();
		setUpIsDone = true;
	}

	@Test
	@Rollback(false)
	public void whenFindById_thenReturnTagEntity() {

		// when
		Optional<TagEntity> found = tagService.findById(tag.getTagId());

		// then
		assertFoundEntity(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnTagEntity() {

		// when
		Optional<TagEntity> found = tagService.findByCode("TST02");

		// then
		assertFoundEntity(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByCode_thenReturnTagDTO() {

		// when
		Optional<TagDTO> found = tagService.findByCode(Constants.localeENGB, "ORG01");

		// then
		assertFoundDTO(found);
	}

	@Test
	@Rollback(false)
	public void whenFindByDesc_thenReturnTagDTO() {

		// when
		Optional<TagDTO> found = tagService.findByDesc(Constants.localeENGB, "ORGANIC");

		// then
		assertFoundDTO(found);
	}

	@Test
	@Rollback(false)
	public void whenFindAllForTestTag_thenReturnTagDTO() {

		Set<String> tagCodes = new HashSet<String>();

		tagCodes.add("ORG01");

		// when
		List<TagDTO> lb = tagService.findAll(Constants.localeENGB, new StringCollectionWrapper(tagCodes));

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(1);
	}

	@Test
	@Rollback(false)
	public void whenFindAllWithNoFacets_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> brandCodes = new HashSet<String>();

		// when
		List<TagDTO> lb = tagService.findAll(Constants.localeENGB, Constants.currencyUSD, "FRT01",
				new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(brandCodes), new Double(1000));

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(2);
	}

	@Test
	@Rollback(false)
	public void whenFindAllWithCategoryFacet_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> brandCodes = new HashSet<String>();

		categoryCodes.add("POM01");

		// when
		List<TagDTO> lt = tagService.findAll(Constants.localeENGB, Constants.currencyUSD, "FRT01",
				new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(brandCodes), new Double(1000));

		// then
		assertNotNull(lt);
		assertThat(lt.size()).isEqualTo(2);
	}

	@Test
	@Rollback(false)
	public void whenFindAllWithPriceFacetHKD_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> brandCodes = new HashSet<String>();

		Double price = new Double("97.2");

		// when
		List<TagDTO> lb = tagService.findAll(Constants.localeENGB, Constants.currencyHKD, "FRT01",
				new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(brandCodes), price);

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(2);
	}

	@Test
	@Rollback(false)
	public void whenFindAllWithPriceFacetUSD_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> brandCodes = new HashSet<String>();

		Double price = new Double("12.4");

		// when
		List<TagDTO> lb = tagService.findAll(Constants.localeENGB, Constants.currencyUSD, "FRT01",
				new StringCollectionWrapper(categoryCodes), new StringCollectionWrapper(brandCodes), price);

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(2);
	}

	private void assertFoundEntity(Optional<TagEntity> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getTagCode()).isEqualTo("TST02");
		assertThat(found.get().getAttributes().stream().filter(t -> t.getLclCd().equals(Constants.localeENGB)).findAny()
				.get().getTagDesc()).isEqualTo("test tag");
	}

	private void assertFoundDTO(Optional<TagDTO> found) {

		assertNotNull(found);

		assertTrue(found.isPresent());

		assertThat(found.get().getTagCode()).isEqualTo("ORG01");
		assertThat(found.get().getTagDesc()).isEqualTo("ORGANIC");

	}

}
