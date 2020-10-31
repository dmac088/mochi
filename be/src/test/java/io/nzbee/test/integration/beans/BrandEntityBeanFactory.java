package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.Constants;
import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.brand.attribute.BrandAttributeEntity;


@Service(value = "brandEntityBeanFactory")
@Profile(value = "tst")
public class BrandEntityBeanFactory {
	
	@Bean
	public final BrandEntity getBrandEntityBean() {
		final BrandEntity brand = new BrandEntity();
		brand.setBrandCode("TST02");

		final BrandAttributeEntity brandAttribute = new BrandAttributeEntity();
		brandAttribute.setBrand(brand);
		brandAttribute.setBrandDesc("test brand");
		brandAttribute.setLclCd(Constants.localeENGB);
		brandAttribute.setBrand(brand);
		brand.addAttribute(brandAttribute);
		
		return brand;
	}
	
}
