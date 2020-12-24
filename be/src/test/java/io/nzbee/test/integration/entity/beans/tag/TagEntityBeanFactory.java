package io.nzbee.test.integration.entity.beans.tag;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.entity.tag.attribute.TagAttributeEntity;

@Service
@Profile(value = "tst")
public class TagEntityBeanFactory implements ITagEntityBeanFactory {
	
	@Override
	public final TagEntity getBean() {
		final TagEntity tag = new TagEntity();
		tag.setTagCode("TST02");

		final TagAttributeEntity tagAttribute = new TagAttributeEntity();
		tagAttribute.setTag(tag);
		tagAttribute.setTagDesc("test tag");
		tagAttribute.setLclCd(Constants.localeENGB);
		tag.setTagAttribute(tagAttribute);
		tag.addTagAttribute(tagAttribute);
		
		return tag;
	}
	
}
