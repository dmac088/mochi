package io.nzbee.domain.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.domain.IDomainObject;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.product.Product;

@JsonTypeName("brand")
public class Brand implements IDomainObject {

	private String brandCode;

	private String brandDesc;
	
	private int objectCount;

	private String locale;
	
	private String currency;
	
	@JsonIgnore
	private List<Product> products;
	
	@JsonIgnore
	private List<BrandCategory> categories;

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
			 	 this.products = new ArrayList<Product>();
			 	 this.categories = new ArrayList<BrandCategory>();
	}
	
	public List<Product> getProducts() {
		return products;
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
