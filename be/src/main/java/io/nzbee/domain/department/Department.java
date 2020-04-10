package io.nzbee.domain.department;

import java.util.ArrayList;
import java.util.List;

import io.nzbee.domain.IDomainObject;
import io.nzbee.domain.product.Product;
 
public class Department implements IDomainObject {

	private String departmentCode;
	
	private String departmentDesc;
	
	private String locale;
	
	private String currency;
	
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
	
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return departmentCode;
	}

	@Override
	public String getDesc() {
		// TODO Auto-generated method stub
		return departmentDesc;
	}

	@Override
	public String getLocale() {
		// TODO Auto-generated method stub
		return locale;
	}

	@Override
	public String getCurrency() {
		// TODO Auto-generated method stub
		return currency;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isHierarchical() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	

}