package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.variables.GeneralVars;


@Service(value = "brandEntityBeanFactory")
@Profile(value = "dev")
public class BrandEntityBeanFactory {
	
	@Bean
	public final Brand getBrandEntityBean() {
		final Brand brand = new Brand();
		brand.setBrandCode("TST02");

		final BrandAttribute brandAttribute = new BrandAttribute();
		brandAttribute.setBrand(brand);
		brandAttribute.setBrandDesc("test brand");
		brandAttribute.setLclCd(GeneralVars.LANGUAGE_ENGLISH);
		brandAttribute.setBrand(brand);
		brand.addAttribute(brandAttribute);
		
		return brand;
	}
	
}
