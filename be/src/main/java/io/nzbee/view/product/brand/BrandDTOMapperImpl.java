package io.nzbee.view.product.brand;

import org.springframework.stereotype.Component;
import io.nzbee.domain.brand.Brand;

@Component
public class BrandDTOMapperImpl implements IBrandDTOMapper {

	@Override
	public io.nzbee.view.product.brand.BrandDTO doToDto(Brand d) {
		io.nzbee.view.product.brand.BrandDTO sp = new io.nzbee.view.product.brand.BrandDTO();
		sp.setBrandCode(d.getBrandCode());
		sp.setBrandDesc(d.getBrandDesc());
		return sp;
	}

	@Override
	public Brand dtoToDo(io.nzbee.view.product.brand.BrandDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
