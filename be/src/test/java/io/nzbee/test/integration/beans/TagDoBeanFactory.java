package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.tag.Tag;


@Service
@Profile(value = "tst")
public class TagDoBeanFactory {

	public final Tag getTagDoBean() {
		
		return new Tag(	"TST01",
							"test tag",
							20,
							"en-GB",
							"HKD");
		
	}
	
}