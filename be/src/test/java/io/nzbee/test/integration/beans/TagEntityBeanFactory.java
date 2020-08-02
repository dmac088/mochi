package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.tag.Tag;
import io.nzbee.entity.tag.attribute.TagAttribute;


@Service(value = "tagEntityBeanFactory")
@Profile(value = "tst")
public class TagEntityBeanFactory {
	
	@Bean
	public final Tag getTagEntityBean() {
		final Tag tag = new Tag();
		tag.setTagCode("TST02");

		final TagAttribute tagAttribute = new TagAttribute();
		tagAttribute.setTag(tag);
		tagAttribute.setTagDesc("test tag");
		tagAttribute.setLclCd(Constants.localeENGB);
		tag.setTagAttribute(tagAttribute);
		tag.addTagAttribute(tagAttribute);
		
		return tag;
	}
	
}
