package io.nzbee.test.integration.beans;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.product.IProductService;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.tag.TagEntity;
import io.nzbee.entity.tag.attribute.TagAttributeEntity;


@Service(value = "tagEntityBeanFactory")
@Profile(value = "tst")
public class TagEntityBeanFactory {
	
	@Autowired
	private IProductService productService;
	
	@Bean
	public final TagEntity getTagEntityBean() {
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
