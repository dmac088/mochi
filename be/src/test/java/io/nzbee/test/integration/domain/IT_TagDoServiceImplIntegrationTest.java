package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.test.integration.domain.beans.tag.ITagDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "it")
@SqlGroup({
	@Sql(scripts = "/database/mochi_schema.sql",
			config = @SqlConfig(dataSource = "mochiDataSourceOwner", 
			transactionManager = "mochiTransactionManagerOwner",
			transactionMode = TransactionMode.ISOLATED)), 
	@Sql(scripts = "/database/mochi_data.sql",
			config = @SqlConfig(dataSource = "mochiDataSource", 
			transactionManager = "mochiTransactionManager",
			transactionMode = TransactionMode.ISOLATED))
})
public class IT_TagDoServiceImplIntegrationTest {

	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
    private ITagPortService tagService;
	
	@Autowired
	private ITagDoBeanFactory tagDoBeanFactory;
	
	private Tag tag = null;
	
	public Tag persistNewTag() {
		tag = tagDoBeanFactory.getBean();
   	
		tagService.save(tag);
	    	
	    return tag;
	}
	
	@Before
	public void setUp() { 
		this.persistNewTag();
	}
	
	@Test
    public void whenValidCode_thenTagShouldBeFound() {
        Tag found = tagService.findByCode(Constants.localeENGB, "ORG01");
      
        assertFound(found);
    }
    
	@Test
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
