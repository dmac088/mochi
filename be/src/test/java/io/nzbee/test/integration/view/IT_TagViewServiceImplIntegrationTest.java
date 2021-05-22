package io.nzbee.test.integration.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.test.integration.view.beans.tag.ITagDoBeanFactory;
import io.nzbee.test.integration.view.beans.tag.TagDoBeanFactory;
import io.nzbee.view.ports.ITagFacetViewPortService;
import io.nzbee.view.product.tag.facet.TagFacetView;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
public class IT_TagViewServiceImplIntegrationTest {

	@TestConfiguration
	static class BrandDoServiceImplIntegrationTest_Configuration {
		// the beans that we need to run this test
		@Bean
		public ITagDoBeanFactory tagDoBeanFactory() {
			return new TagDoBeanFactory();
		}
		
	}
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
    private ITagFacetViewPortService tagService;
	
	@Autowired
	private ITagDoBeanFactory tagDoBeanFactory;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	private static boolean setUpIsDone = false;
	
	private static TagFacetView tag = null;
	
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
		setUpIsDone = true;
	}
	
	private void assertFound(TagFacetView found) {

		assertNotNull(found);
		
		assertThat(found.getTagCode())
	       .isEqualTo("ORG01");
		
		assertThat(found.getTagDesc())
	       .isEqualTo("ORGANIC");
	    	
	}
}
