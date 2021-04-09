package io.nzbee.entity.brand.domain;

import org.springframework.stereotype.Component;

import io.nzbee.domain.brand.Brand;
import io.nzbee.entity.brand.BrandEntity;

@Component
public class BrandDomainMapperImpl implements IBrandDomainMapper {

	@Override
	public Brand DTOToDo(BrandDomainDTO dto) {
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
