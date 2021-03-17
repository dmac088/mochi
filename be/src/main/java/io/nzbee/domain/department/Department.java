package io.nzbee.domain.department;

import java.util.ArrayList;
import java.util.List;
import io.nzbee.domain.ILocalizedDomainObject;
import io.nzbee.domain.product.Product;

public class Department implements ILocalizedDomainObject {

	private String departmentCode;
	
	private String departmentDesc;

	private String locale;
	
	private List<Product> products;
	
	public Department(	String departmentCode,
						String departmentDesc,
						String locale) {
						this.departmentCode = departmentCode;
						this.departmentDesc = departmentDesc;
						this.locale = locale;
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

	public List<Product> getProducts() {
		return products;
	}
	

}