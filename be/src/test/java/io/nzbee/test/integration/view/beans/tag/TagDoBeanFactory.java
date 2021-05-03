package io.nzbee.test.integration.view.beans.tag;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.view.product.tag.facet.TagFacetView;

@Service
@Profile("it")
public class TagDoBeanFactory implements ITagDoBeanFactory {

	@Override
	public TagFacetView getBean() {
		
		return new TagFacetView(	"TST01",
						"test tag",
						new Long(20),
						"en-GB");
		
	}
	
}
