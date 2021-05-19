package io.nzbee.entity.brand.view;

import org.springframework.stereotype.Component;
import io.nzbee.view.product.brand.BrandView;

@Component
public class BrandDTOMapperImpl implements IBrandDTOMapper {

	@Override
	public BrandView toView(BrandDTO d) {
		return new BrandView(
				d.getBrandCode(),
				d.getBrandDesc(),
				d.getLocale()
		);
	}

}
