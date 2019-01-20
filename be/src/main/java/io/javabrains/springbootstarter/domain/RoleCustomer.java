package io.javabrains.springbootstarter.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "customer", schema = "mochi")
@PrimaryKeyJoinColumn(name = "rle_id")
public class RoleCustomer extends Role {
 
	@Transient
	private Long roleTypeId = (long) 1;
	
	public RoleCustomer() {
		super();
		this.setRoleType(new RoleType(roleTypeId));
	}
	
	@Column(name="cst_id", insertable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String CustomerNumber;

	public String getCustomerNumber() {
		return CustomerNumber;
	}

}
