package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.dto.brand.Brand;

@Service(value = "brandDtoBeanFactory")
@Profile(value = "dev")
public class BrandDtoBeanFactory {
	
	@Bean
	public final Brand getBrandDtoBean() {
		
		final Brand brand = new Brand();
		brand.setBrandCode("TST02");
		brand.setBrandDesc("test brand");
		brand.setLocale("en-GB");
		
		return brand;
	}
	
}
