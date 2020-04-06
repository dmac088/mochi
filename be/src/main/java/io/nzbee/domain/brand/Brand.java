package io.nzbee.domain.brand;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.domain.IDomainObject;

@JsonTypeName("brand")
public class Brand implements IDomainObject {

	private String brandCode;

	private String brandDesc;
	
	private int objectCount;

	private String locale;
	
	private String currency;
	
	public Brand(String brandCode,
				 String brandDesc,
				 int objectCount,
				 String locale, 
				 String currency) {
		this.brandCode = brandCode;
		this.brandDesc = brandDesc;
		this.objectCount = objectCount;
		this.locale = locale;
		this.currency = currency;
	}
	
	public String getBrandCode() {
		return brandCode;
	}

	public String getBrandDesc() {
		return brandDesc;
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
	public String getLocale() {
		return locale;
	}
	
	@Override
	public String getCurrency() {
		return currency;
	}

	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Brand pcDto = (Brand) o;
	     return this.getCode() == pcDto.getCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getCode());
	}
	
}
