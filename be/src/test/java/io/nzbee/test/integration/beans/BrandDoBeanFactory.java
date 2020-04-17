package io.nzbee.test.integration.beans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.domain.brand.Brand;


@Service
@Profile(value = "dev")
public class BrandDoBeanFactory {

	public final Brand getBrandDoBean() {
		
		Brand brand = new Brand( "TST03",
								 "test brand",
								 10,
								 "en-GB", 
								 "HKD");
		
		return brand;
		
	}
	
}
