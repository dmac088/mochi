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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.test.unit.domain.beans.TagDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
public class IT_TagDoServiceImplIntegrationTest {

	@TestConfiguration
    static class TagServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
		
    }
	
	@MockBean
    private JavaMailSender mailSender;
	
	@Autowired
    private ITagPortService tagService;
	
	@Autowired
	private TagDoBeanFactory tagDoBeanFactory;
	
	private Tag tag = null;
	
	public Tag persistNewTag() {
		tag = tagDoBeanFactory.getTagDoBean();
   	
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
