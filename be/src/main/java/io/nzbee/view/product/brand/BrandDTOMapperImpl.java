package io.nzbee.view.product.brand;

import org.springframework.stereotype.Component;
import io.nzbee.entity.brand.BrandDTO;

@Component
public class BrandDTOMapperImpl implements IBrandDTOMapper {

	@Override
	public io.nzbee.view.product.brand.BrandDTO doToDto(BrandDTO d) {
		io.nzbee.view.product.brand.BrandDTO sp = new io.nzbee.view.product.brand.BrandDTO();
		sp.setBrandCode(d.getBrandCode());
		sp.setBrandDesc(d.getBrandDesc());
		return sp;
	}

	@Override
	public BrandDTO dtoToDo(io.nzbee.view.product.brand.BrandDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
