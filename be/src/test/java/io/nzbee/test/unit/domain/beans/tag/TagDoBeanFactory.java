package io.nzbee.test.unit.domain.beans.tag;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.view.product.tag.facet.TagFacetView;

@Service
@Profile("ut")
public class TagDoBeanFactory implements ITagDoBeanFactory {

	@Override
	public TagFacetView getBean() {
		
		return new TagFacetView(	"TST01",
						"test tag",
						Long.valueOf(20),
						"en-GB");
		
	}
	
}
