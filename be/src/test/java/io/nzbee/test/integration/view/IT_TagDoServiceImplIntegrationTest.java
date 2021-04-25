package io.nzbee.test.integration.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import java.sql.SQLException;
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
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.test.integration.view.beans.tag.ITagDoBeanFactory;
import io.nzbee.test.integration.view.beans.tag.TagDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("it")
public class IT_TagDoServiceImplIntegrationTest {

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
    private ITagPortService tagService;
	
	@Autowired
	private ITagDoBeanFactory tagDoBeanFactory;
	
	@Autowired
	@Qualifier("mochiDataSourceOwner")
	private DataSource database;
	
	private static boolean setUpIsDone = false;
	
	private static Tag tag = null;
	
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
		this.persistNewTag();
		setUpIsDone = true;
	}
	
	public Tag persistNewTag() {
		tag = tagDoBeanFactory.getBean();
   	
		tagService.save(tag);
	    	
	    return tag;
	}
	
	@Test
	@Rollback(false)
    public void whenValidCode_thenTagShouldBeFound() {
        Tag found = tagService.findByCode(Constants.localeENGB, "ORG01");
      
        assertFound(found);
    }
    
	@Test
	@Rollback(false)
    public void whenValidDesc_thenTagShouldBeFound() {
        Tag found = tagService.findByDesc(Constants.localeENGB, "ORGANIC");
      
        assertFound(found);
    }
	
	private void assertFound(Tag found) {

		assertNotNull(found);
		
		assertThat(found.getTagCode())
	       .isEqualTo("ORG01");
		
		assertThat(found.getTagDesc())
	       .isEqualTo("ORGANIC");
	    	
	}
}
