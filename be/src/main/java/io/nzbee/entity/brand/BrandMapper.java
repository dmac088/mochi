package io.nzbee.entity.brand;

import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper implements IBrandMapper {

	@Override
	public Optional<io.nzbee.domain.brand.Brand> entityToDo(Optional<Brand> e, String locale, String currency) {
		if(!e.isPresent()) { return Optional.ofNullable(null); }
		Brand be = e.get();
		io.nzbee.domain.brand.Brand bo = 
				new io.nzbee.domain.brand.Brand(
						 be.getBrandCode(),
						 be.getBrandAttribute().getBrandDesc(),
						 0,
						 locale, 
						 currency);
	
		return Optional.ofNullable(bo);
	}

}
