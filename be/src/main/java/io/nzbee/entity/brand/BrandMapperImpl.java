package io.nzbee.entity.brand;

import org.springframework.stereotype.Component;

@Component
public class BrandMapperImpl implements IBrandMapper {

	@Override
	public io.nzbee.domain.brand.Brand entityToDo(BrandEntity e, String locale, String currency) {

		io.nzbee.domain.brand.Brand bo = 
				new io.nzbee.domain.brand.Brand(
						 e.getBrandCode(),
						 e.getBrandAttribute().getBrandDesc(),
						 e.getCount(),
						 locale);
	
		return bo;
	}

	@Override
	public io.nzbee.domain.brand.Brand entityToDo(BrandEntity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BrandEntity doToEntity(io.nzbee.domain.brand.Brand d) {
		// TODO Auto-generated method stub
		return null;
	}

}
