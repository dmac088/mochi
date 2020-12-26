package io.nzbee.test.unit.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import io.nzbee.Constants;
import io.nzbee.domain.tag.Tag;
import io.nzbee.domain.tag.TagServiceImpl;
import io.nzbee.domain.tag.ITagService;
import io.nzbee.domain.ports.ITagPortService;
import io.nzbee.entity.adapters.PostgresTagAdapter;
import io.nzbee.test.unit.domain.beans.tag.TagDoBeanFactory;
import io.nzbee.test.unit.domain.beans.tag.ITagDoBeanFactory;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "ut")
public class UT_TagTest {

	@TestConfiguration
	static class ProductTagDomainServiceImplUnitTest {
		// the beans that we need to run this test
		
		@Bean
		public TagDoBeanFactory tagDoBeanFactory() {
			return new TagDoBeanFactory();
		}
		
		@Bean
		public ITagPortService tagPortService() {
			return new PostgresTagAdapter();
		}
		
		@Bean 
		public ITagService tagService() {
			return new TagServiceImpl();
		}
		
	}
	
	@Autowired
	private ITagService tagService;

	@MockBean
	private ITagPortService tagPortService;

	@Autowired
	private ITagDoBeanFactory tagDoBeanFactory;
	
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.initMocks(this);

		Tag tag = tagDoBeanFactory.getBean();

		// need to fill more of the properties here
		Mockito.when(tagPortService.findByCode(Constants.localeENGB,
												tag.getTagCode())).thenReturn(tag);
		
		Mockito.when(tagPortService.findByDesc(Constants.localeENGB,
												tag.getTagDesc())).thenReturn(tag);
	}

	@Test
	public void whenFindByCode_thenProductTagIsFound() {
		String code = "TST01";

		Tag found = tagService.findByCode(Constants.localeENGB,
											  code);

		assertFound(found);
	}
	
	@Test
	public void whenFindByDesc_thenProductTagIsFound() {
		String desc = "test tag";

		Tag found = tagService.findByDesc(Constants.localeENGB,
											  desc);

		assertFound(found);
	}
	
	
    private void assertFound(Tag found) {

    	assertNotNull(found);
    	
    	assertThat(found.getTagCode())
        .isEqualTo("TST01");
    	
	    assertThat(found.getTagDesc())
	    .isEqualTo("test tag");
    }


}
