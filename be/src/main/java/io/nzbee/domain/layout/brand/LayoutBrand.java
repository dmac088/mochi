package io.nzbee.domain.layout.brand;

import java.util.ArrayList;
import java.util.List;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.layout.Layout;

public class LayoutBrand extends Layout {

	private List<Brand> brands;
	
	public LayoutBrand(String layoutCode, String layoutDesc, Brand brand) {
		super(layoutCode, layoutDesc);
		brands = new ArrayList<Brand>();
		brands.add(brand);
	}
	
}
