package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Globals;
import io.nzbee.entity.tag.ITagService;
import io.nzbee.entity.tag.Tag;
import io.nzbee.test.integration.beans.TagEntityBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
@SqlGroup({
		@Sql(scripts = "/database/mochi_schema.sql", config = @SqlConfig(dataSource = "mochiDataSourceOwner", transactionManager = "mochiTransactionManagerOwner", transactionMode = TransactionMode.ISOLATED)),
		@Sql(scripts = "/database/mochi_data.sql", config = @SqlConfig(dataSource = "mochiDataSource", transactionManager = "mochiTransactionManager", transactionMode = TransactionMode.ISOLATED)) })
public class IT_TagEntityRepositoryIntegrationTest {

	@TestConfiguration
	static class TagEntityRepositoryIntegrationTest {

	}

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private Globals globalVars;

	@Autowired
	private TagEntityBeanFactory tagEntityBeanFactory;

	@Autowired
	private ITagService tagService;

	private Tag tag = null;

	@Before
	public void persistNewTag() {

		tag = tagEntityBeanFactory.getTagEntityBean();

		// persist a new transient test tag
		tagService.save(tag);

	}

	@Test
	public void whenFindById_thenReturnTag() {

		// when
		Tag found = tagService.findById(globalVars.getLocaleENGB(), globalVars.getCurrencyUSD(), tag.getTagId())
				.get();

		// then
		assertFound(found);
	}

	// write test cases here
	@Test
	public void whenFindByCode_thenReturnTag() {

		// when
		Tag found = tagService.findByCode("en-GB", "USD", "TST02").get();

		// then
		assertFound(found);
	}

	// write test cases here
	@Test
	public void whenFindByDesc_thenReturnTag() {

		// when
		Tag found = tagService.findByDesc(globalVars.getLocaleENGB(), globalVars.getCurrencyUSD(), "test tag")
				.get();

		// then
		assertFound(found);
	}

	@Test
	public void whenFindAllWithNoFacets_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> brandCodes = new HashSet<String>();

		// when
		List<Tag> lb = tagService.findAll(globalVars.getLocaleENGB(), globalVars.getCurrencyUSD(), "FRT01",
				categoryCodes, brandCodes);

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(2);
	}

	@Test
	public void whenFindAllWithCategoryFacet_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> brandCodes = new HashSet<String>();

		categoryCodes.add("POM01");

		// when
		List<Tag> lb = tagService.findAll(globalVars.getLocaleENGB(), globalVars.getCurrencyUSD(), "FRT01",
				categoryCodes, brandCodes);

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(1);
	}

	private void assertFound(final Tag found) {

		assertThat(found.getCode()).isEqualTo("TST02");
		assertThat(found.getTagAttribute().getTagDesc()).isEqualTo("test tag");
	}

	@After
	public void closeConnection() {
		entityManager.close();
	}

}
