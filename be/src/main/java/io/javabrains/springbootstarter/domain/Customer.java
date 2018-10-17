package io.javabrains.springbootstarter.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "customer", schema = "mochi")
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
