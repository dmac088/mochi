package io.nzbee.entity.brand;

import org.springframework.stereotype.Component;

import io.nzbee.domain.brand.Brand;

@Component
public class BrandMapperImpl implements IBrandMapper {

	@Override
	public Brand DTOToDo(BrandDTO dto) {
		Brand bo = 
				new Brand(	dto.getBrandCode(),
							dto.getBrandDesc(),
							dto.getCount(),
							dto.getLocale());
	
		return bo;
	}

	@Override
	public BrandEntity doToEntity(Brand d) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
