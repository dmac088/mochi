package io.nzbee.domain.brand;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.nzbee.SpringContext;
import io.nzbee.domain.IDomainObject;
import io.nzbee.domain.IService;

public class Brand implements IDomainObject {

	private String brandCode;

	private String brandDesc;
	
	private int objectCount;
	
	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandDesc() {
		return brandDesc;
	}

	public void setBrandDesc(String brandDesc) {
		this.brandDesc = brandDesc;
	}
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Brand pcDto = (Brand) o;
	     return this.brandCode == pcDto.brandCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brandCode);
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return this.brandCode;
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return false; 
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return objectCount;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return this.brandDesc;
	}

	@Override
	@JsonIgnore
	public IService getServiceBean() {
		// TODO Auto-generated method stub
		return SpringContext.getBean(BrandServiceImpl.class);
	}
	
}
