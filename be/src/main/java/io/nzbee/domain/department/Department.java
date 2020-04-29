package io.nzbee.domain.department;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nzbee.domain.ILocalizedDomainObject;
import io.nzbee.domain.product.Product;

public class Department implements ILocalizedDomainObject {

	private String departmentCode;
	
	private String departmentDesc;

	private String locale;
	
	private String currency;
	
	@JsonIgnore
	private List<Product> products;
	
	public Department(	String departmentCode,
						String departmentDesc,
						String locale, 
						String currency) {
						this.departmentCode = departmentCode;
						this.departmentDesc = departmentDesc;
						this.locale = locale;
						this.currency = currency;
						this.products = new ArrayList<Product>();
	}
		
	public String getDepartmentCode() {
		return departmentCode;
	}


	public String getDepartmentDesc() {
		return departmentDesc;
	}
	
	@Override
	public String getLocale() {
		return locale;
	}

	@Override
	public String getCurrency() {
		return currency;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	

}