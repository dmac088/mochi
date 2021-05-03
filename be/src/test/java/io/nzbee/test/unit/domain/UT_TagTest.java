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
import io.nzbee.test.unit.domain.beans.tag.TagDoBeanFactory;
import io.nzbee.view.ports.ITagFacetViewPortService;
import io.nzbee.view.product.tag.facet.ITagFacetService;
import io.nzbee.view.product.tag.facet.TagFacetView;
import io.nzbee.view.product.tag.facet.TagFacetServiceImpl;
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
		public ITagFacetService tagService() {
			return new TagFacetServiceImpl();
		}
		
	}
	
	@Autowired
	private ITagFacetService tagService;

	@MockBean
	private ITagFacetViewPortService tagPortService;

	@Autowired
	private ITagDoBeanFactory tagDoBeanFactory;
	
	@Before
	public void setUp() {
		// we setup a mock so that when
		MockitoAnnotations.initMocks(this);

		TagFacetView tag = tagDoBeanFactory.getBean();

		// need to fill more of the properties here
		Mockito.when(tagPortService.findByCode(Constants.localeENGB,
												tag.getTagCode())).thenReturn(tag);
		
		Mockito.when(tagPortService.findByDesc(Constants.localeENGB,
												tag.getTagDesc())).thenReturn(tag);
	}

	@Test
	public void whenFindByCode_thenProductTagIsFound() {
		String code = "TST01";

		TagFacetView found = tagService.findByCode(Constants.localeENGB,
											  code);

		assertFound(found);
	}
	
	@Test
	public void whenFindByDesc_thenProductTagIsFound() {
		String desc = "test tag";

		TagFacetView found = tagService.findByDesc(Constants.localeENGB,
											  desc);

		assertFound(found);
	}
	
	
    private void assertFound(TagFacetView found) {

    	assertNotNull(found);
    	
    	assertThat(found.getTagCode())
        .isEqualTo("TST01");
    	
	    assertThat(found.getTagDesc())
	    .isEqualTo("test tag");
    }


}
