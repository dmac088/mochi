package io.nzbee.test.unit.domain.beans;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.domain.brand.Brand;


@Service
@Profile(value = "tst")
public class BrandDoBeanFactory {

	public final Brand getBrandDoBean() {
		
		Brand brand = new Brand( "TST03",
								 "test brand",
								 "en-GB");
		
		return brand;
		
	}
	
}
