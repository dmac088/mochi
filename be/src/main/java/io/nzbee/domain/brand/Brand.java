package io.nzbee.domain.brand;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.nzbee.domain.ILocalizedDomainObject;
import io.nzbee.domain.category.BrandCategory;
import io.nzbee.domain.product.Product;

@JsonTypeName("brand")
public class Brand implements ILocalizedDomainObject {

	private String brandCode;

	private String brandDesc;
	
	private String locale;
	
	private int objectCount;
	
	@JsonIgnore
	private List<Product> products;
	
	@JsonIgnore
	private List<BrandCategory> categories;

	public Brand(String brandCode,
				 String brandDesc,
				 int objectCount,
				 String locale) {
				 this.brandCode = brandCode;
				 this.brandDesc = brandDesc;
			 	 this.products = new ArrayList<Product>();
			 	 this.categories = new ArrayList<BrandCategory>();
			 	 this.locale = locale;
			 	 this.objectCount = objectCount;
	}
	
	public int getCount() {
		return objectCount;
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
	public String getLocale() {
		return this.locale;
	}
	
	@Override
	public boolean equals(Object o) {
		 if (this == o) return true;
	     if (o == null || getClass() != o.getClass()) return false;
	     Brand pcDto = (Brand) o;
	     return this.getBrandCode() == pcDto.getBrandCode();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getBrandCode());
	}


	
}
