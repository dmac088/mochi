package io.nzbee.entity.brand.view.facet;

import io.nzbee.view.product.brand.facet.BrandFacetView;

public class BrandFacetViewMapperImpl implements IBrandFacetViewMapper {
 
	@Override
	public BrandFacetView toView(BrandFacetViewDTO d) {
		
		BrandFacetView bfv = new BrandFacetView();
		bfv.setBrandCode(d.getBrandCode());
		bfv.setBrandDesc(d.getBrandDesc());
		bfv.setLocale(d.getLocale());
		bfv.setObjectCount(d.getCount());
		
		return bfv;
	}
	
}
