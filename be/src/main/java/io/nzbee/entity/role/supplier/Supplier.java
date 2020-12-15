package io.nzbee.entity.role.supplier;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Transient;
import io.nzbee.entity.role.Role;

@Entity
@Table(name = "supplier", schema = "mochi")
@DiscriminatorValue("2")
public class Supplier extends Role {
	
	@Transient
	private Long roleTypeId = (long) 2;
	
	@Column(name="sup_num", insertable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String supplierNumber;

	public String getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}
	
}
