package io.nzbee.test.integration.domain.beans.brand;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import io.nzbee.domain.brand.Brand;

@Service
@Profile("it")
public class BrandDoBeanFactory implements IBrandDoBeanFactory {

	@Override
	public Brand getBean() {
		
		return new Brand( 	"ENZ01",
							"Enza",
							"en-GB");
		
	}
	
}
