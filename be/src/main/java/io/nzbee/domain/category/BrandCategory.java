package io.nzbee.domain.category;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nzbee.SpringContext;
import io.nzbee.domain.IService;
import io.nzbee.domain.brand.Brand;
import io.nzbee.domain.brand.BrandServiceImpl;
public class BrandCategory extends Category {


	public BrandCategory() {
		super();
	}

	@JsonIgnore
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
	public String getDesc() {
		// TODO Auto-generated method stub
		return super.getCategoryDesc();
	}

	@Override
	@JsonIgnore
	public IService getServiceBean() {
		// TODO Auto-generated method stub
		return SpringContext.getBean(CategoryServiceImpl.class);
	}
	
}
