package io.nzbee.test.integration.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import io.nzbee.entity.tag.TagDTO;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.test.integration.beans.entity.TagEntityBeanFactory;

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
	
	@MockBean
    private JavaMailSender mailSender;

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager entityManager;

	@Autowired
	private TagEntityBeanFactory tagEntityBeanFactory;

	@Autowired
	private ITagService tagService;

	private TagEntity tag = null;

	@Before
	public void persistNewTag() {

		tag = tagEntityBeanFactory.getTagEntityBean();

		// persist a new transient test tag
		tagService.save(tag);
	}

	@Test
	public void whenFindById_thenReturnTag() {
		
		// when
		Optional<TagEntity> found = tagService.findById(tag.getTagId());

		// then
		assertFound(found);
	}

	// write test cases here
	@Test
	public void whenFindByCode_thenReturnTag() {

		// when
		Optional<TagEntity> found = tagService.findByCode("TST02");

		// then
		assertFound(found);
	}

	// write test cases here
	@Test
	public void whenFindByDesc_thenReturnTag() {

		// when
		Optional<TagEntity> found = tagService.findEntityByDesc(Constants.localeENGB, "test tag");

		// then
		assertFound(found);
	}
	
	@Test
	public void whenFindAllForTestTag_thenReturnTheTestTag() {

		Set<String> tagCodes = new HashSet<String>();
		
		tagCodes.add("ORG01");

		// when
		List<TagDTO> lb = tagService.findAll(Constants.localeENGB, tagCodes);

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(1);
	}

	@Test
	public void whenFindAllWithNoFacets_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> brandCodes = new HashSet<String>();

		// when
		List<TagDTO> lb = tagService.findAll( Constants.localeENGB, 
											  Constants.currencyUSD, 
											  "FRT01",
											  categoryCodes, 
											  brandCodes,
											  new Double(1000));

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
		List<TagDTO> lt = tagService.findAll(	Constants.localeENGB, 
												Constants.currencyUSD, 
												"FRT01",
												categoryCodes, 
												brandCodes,
												new Double(1000));

		// then
		assertNotNull(lt);
		assertThat(lt.size()).isEqualTo(2);
	}
	
	
	@Test
	public void whenFindAllWithPriceFacetHKD_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> brandCodes = new HashSet<String>();

		Double price = new Double("97.2");

		// when
		List<TagDTO> lb = tagService.findAll(	Constants.localeENGB, 
												Constants.currencyHKD, 
												"FRT01",
												categoryCodes, 
												brandCodes,
												price);

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(2);
	}
	
	@Test
	public void whenFindAllWithPriceFacetUSD_thenReturnCorrectResultCount() {

		Set<String> categoryCodes = new HashSet<String>();
		Set<String> brandCodes = new HashSet<String>();

		Double price = new Double("12.4");

		// when
		List<TagDTO> lb = tagService.findAll(	Constants.localeENGB, 
												Constants.currencyUSD, 
												"FRT01",
												categoryCodes, 
												brandCodes,
												price);

		// then
		assertNotNull(lb);
		assertThat(lb.size()).isEqualTo(2);
	}

	private void assertFound(Optional<TagEntity> found) {
		
		assertNotNull(found);
		
		assertTrue(found.isPresent());

		assertThat(found.get().getTagCode()).isEqualTo("TST02");
		assertThat(found.get().getAttributes().stream().filter(t -> t.getLclCd().equals(Constants.localeENGB)).findAny().get().getTagDesc()).isEqualTo("test tag");
		
	}

	@After
	public void closeConnection() {
		entityManager.close();
	}

}
