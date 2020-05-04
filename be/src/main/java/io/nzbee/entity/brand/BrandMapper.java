package io.nzbee.entity.brand;

import org.springframework.stereotype.Component;

@Component
public class BrandMapper implements IBrandMapper {

	@Override
	public io.nzbee.domain.brand.Brand entityToDo(Brand e, String locale, String currency) {

		io.nzbee.domain.brand.Brand bo = 
				new io.nzbee.domain.brand.Brand(
						 e.getBrandCode(),
						 e.getBrandAttribute().getBrandDesc(),
						 0,
						 locale, 
						 currency);
	
		return bo;
	}

}
