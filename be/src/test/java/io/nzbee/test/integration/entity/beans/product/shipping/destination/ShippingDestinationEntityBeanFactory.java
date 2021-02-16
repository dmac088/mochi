package io.nzbee.test.integration.entity.beans.product.shipping.destination;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationEntity;
import io.nzbee.entity.product.shipping.destination.attribute.ShippingDestinationAttributeEntity;

@Service
@Profile(value = "it")
public class ShippingDestinationEntityBeanFactory implements IShippingDestinationEntityBeanFactory {

	@Override
	public ShippingDestinationEntity getBean() {
		
		ShippingDestinationEntity sd = new ShippingDestinationEntity();
		
		sd.setShippingDestinationCode("TST01");
		sd.setShippingDestinationDesc("Test shipping destination description");
		sd.setShippingDestinationShortCode("ZZ");
		sd.setShippingZoneCode("TZ");
		
		ShippingDestinationAttributeEntity sdaEn = new ShippingDestinationAttributeEntity();
		sdaEn.setShippingDestination(sd);
		sdaEn.setShippingDestinationDesc("Test localized shipping destination description");
		sdaEn.setLclCd(Constants.localeENGB);
		sd.getAttributes().add(sdaEn);
		
		ShippingDestinationAttributeEntity sdaTc = new ShippingDestinationAttributeEntity();
		sdaTc.setShippingDestination(sd);
		sdaTc.setShippingDestinationDesc("測試本地化的運送目的地描述");
		sdaTc.setLclCd(Constants.localeZHHK);
		sd.getAttributes().add(sdaTc);
		
		return sd;
	}

}
