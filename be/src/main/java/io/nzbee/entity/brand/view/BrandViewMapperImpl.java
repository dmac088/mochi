package io.nzbee.entity.brand.view;

import io.nzbee.view.product.brand.BrandView;

public class BrandViewMapperImpl implements IBrandViewMapper {

	@Override
	public BrandView toView(BrandViewDTO d) {
		return new BrandView(
				d.getBrandCode(),
				d.getBrandDesc(),
				d.getLocale()
		);
	}

}
