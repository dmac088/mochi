package io.nzbee.view.product.brand;

import org.springframework.stereotype.Component;
import io.nzbee.domain.brand.Brand;

@Component
public class BrandViewMapperImpl implements IBrandViewMapper {

	@Override
	public BrandView toView(Brand d) {
		BrandView sp = new BrandView();
		sp.setBrandCode(d.getBrandCode());
		sp.setBrandDesc(d.getBrandDesc());
		sp.setLocale(d.getLocale());
		return sp;
	}

}
