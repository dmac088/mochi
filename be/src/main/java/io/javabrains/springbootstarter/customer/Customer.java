package io.javabrains.springbootstarter.customer;


import io.javabrains.springbootstarter.role.Role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity(name = "customer")
@PrimaryKeyJoinColumn(name = "rle_id")
public class Customer extends Role {
 
	public Customer() {
		super();
	}
	
	@Column(name="cst_id")
	private String CustomerId;

	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}
}
