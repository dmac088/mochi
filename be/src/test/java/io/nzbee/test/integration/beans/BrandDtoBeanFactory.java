package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.dto.brand.Brand;

@Service(value = "brandDtoBeanFactory")
@Profile(value = "dev")
public class BrandDtoBeanFactory {
	
	public final Brand getBrandDtoBean() {
		
		final Brand brand = new Brand();
		brand.setBrandCode("TST02");
		brand.setBrandDesc("test brand dto");
		brand.setLocale("en-GB");
		brand.setCurrency("USD");
		
		return brand;
	}
	
}
