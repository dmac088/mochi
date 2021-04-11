package io.nzbee.entity.brand.view;

import org.springframework.stereotype.Component;
import io.nzbee.view.product.brand.BrandView;

@Component
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
