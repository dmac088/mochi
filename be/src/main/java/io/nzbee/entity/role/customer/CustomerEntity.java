package io.nzbee.entity.role.customer;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.nzbee.entity.role.Role;
import io.nzbee.entity.role.RoleType;


@Entity
@Table(name = "customer", schema = "mochi")
@PrimaryKeyJoinColumn(name = "rle_id")
@DiscriminatorValue("1")
public class CustomerEntity extends Role {
 
	@Transient
	private Long roleTypeId = (long) 1;
	
	public CustomerEntity() {
		super();
		this.setRoleType(new RoleType(roleTypeId));
	}
	
	@Column(name="cst_num", insertable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String customerNumber;

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

}
