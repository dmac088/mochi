package io.nzbee.domain.category;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.domain.brand.Brand;

@JsonTypeName("brandcategory")
public class BrandCategory extends Category {

	public BrandCategory(	String categoryCode,
							String categoryDesc,
							boolean isHierarchical,
							Long level,
							int objectCount,
							String parentCode,
							String locale, 
							String currency) {
		super(categoryCode, 
			  categoryDesc, 
			  "brandcategory",
			  level, 
			  locale, 
			  currency,
			  parentCode,
			  objectCount);
		this.brands = new ArrayList<Brand>();
	}

	@JsonIgnore
	private List<Brand> brands;
	
	public List<Brand> getBrands() {
		return brands;
	}
	
	public void addBrand(Brand brand) {
		this.getBrands().add(brand);
	}
	
	public void removeBrand(Brand brand) {
		this.getBrands().remove(brand);
	}

	@Override
	public String getCode() {
		return super.getCategoryCode();
	}

	@Override
	public boolean isHierarchical() {
		return false;
	}

	@Override
	public Long getLevel() {
		return super.getCategoryLevel();
	}

	@Override
	public String getDesc() {
		return super.getCategoryDesc();
	}
	
}
