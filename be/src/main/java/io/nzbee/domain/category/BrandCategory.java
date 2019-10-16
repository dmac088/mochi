package io.nzbee.domain.category;

import java.util.List;

import io.nzbee.domain.brand.Brand;
import io.nzbee.ui.component.web.facet.EntityFacet;

public class BrandCategory extends Category {


	public BrandCategory() {
		super();
	}

	private List<Brand> brands;
	
	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return super.getCategoryCode();
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return super.getCategoryDesc();
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long getLevel() {
		// TODO Auto-generated method stub
		return super.getCategoryLevel();
	}

	@Override
	public EntityFacet toFacet() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
