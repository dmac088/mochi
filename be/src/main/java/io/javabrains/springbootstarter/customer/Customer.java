package io.javabrains.springbootstarter.customer;


import io.javabrains.springbootstarter.role.Role;
import io.javabrains.springbootstarter.role.RoleType;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "Customer")
@PrimaryKeyJoinColumn(name = "rle_id")
public class Customer extends Role {

	public Customer() {
		super();
	}
	
	
	
}
