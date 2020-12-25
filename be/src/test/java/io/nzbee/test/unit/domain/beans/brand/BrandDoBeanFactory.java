package io.nzbee.test.unit.domain.beans.brand;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import io.nzbee.domain.brand.Brand;


@Service
@Profile(value = "ut")
public class BrandDoBeanFactory implements IBrandDoBeanFactory {

	public final Brand getBean() {
		
		Brand brand = new Brand( "TST03",
								 "test brand",
								 "en-GB");
		
		return brand;
		
	}
	
}
