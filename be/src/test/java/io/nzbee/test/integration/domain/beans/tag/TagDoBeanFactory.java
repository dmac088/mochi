package io.nzbee.test.integration.domain.beans.tag;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.tag.Tag;


@Service
@Profile(value = "it")
public class TagDoBeanFactory implements ITagDoBeanFactory {

	@Override
	public Tag getBean() {
		
		return new Tag(	"TST01",
							"test tag",
							new Long(20),
							"en-GB");
		
	}
	
}
