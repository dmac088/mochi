package io.nzbee.test.integration.entity.beans.product.shipping.type;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.product.shipping.type.ShippingTypeEntity;
import io.nzbee.entity.product.shipping.type.attribute.ShippingTypeAttributeEntity;

@Service
@Profile(value = "it")
public class ShippingTypeEntityBeanFactory implements IShippingTypeEntityBeanFactory {

	@Override
	public ShippingTypeEntity getBean() {
		
		ShippingTypeEntity sd = new ShippingTypeEntity();
		
		sd.setShippingTypeCode("TST01");
		ShippingTypeAttributeEntity sdaEn = new ShippingTypeAttributeEntity();
		sdaEn.setShippingType(sd);
		sdaEn.setShippingTypeDesc("Test localized shipping type description");
		sdaEn.setLclCd(Constants.localeENGB);
		sd.getAttributes().add(sdaEn);
		
		ShippingTypeAttributeEntity sdaTc = new ShippingTypeAttributeEntity();
		sdaTc.setShippingType(sd);
		sdaTc.setShippingTypeDesc("測試本地化的運送目的地描述");
		sdaTc.setLclCd(Constants.localeZHHK);
		sd.getAttributes().add(sdaTc);
		
		return sd;
	}

}
