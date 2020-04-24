package io.nzbee.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.domain.tag.ITagService;
import io.nzbee.domain.tag.Tag;
import io.nzbee.test.integration.beans.TagDoBeanFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles(profiles = "tst")
public class IT_TagDoServiceImplIntegrationTest {

	@TestConfiguration
    static class TagServiceImplIntegrationTestConfiguration {
		//the beans that we need to run this integration test
		
    }
	
	@Autowired
    private ITagService tagService;
	
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
        Tag found = tagService.findByCode("en-GB", "HKD", "TST01").get();
      
        assertFound(found);
    }
    
	@Test
    public void whenValidDesc_thenTagShouldBeFound() {
        Tag found = tagService.findByDesc("en-GB", "HKD", "test tag").get();
      
        assertFound(found);
    }
	
	private void assertFound(final Tag found) {

		assertThat(found.getCode())
	       .isEqualTo("TST01");
		
		assertThat(found.getDesc())
	       .isEqualTo("test tag");
	    	
	}
}
