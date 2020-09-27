package io.nzbee.entity.inventory.supplier;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Supplier {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sup_id")
	private Long supplierId;
	
	@Column(name="sup_cd")
	private String supplierCode;
	
	@Column(name="sup_desc")
	private String supplierDesc;

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierDesc() {
		return supplierDesc;
	}

	public void setSupplierDesc(String supplierDesc) {
		this.supplierDesc = supplierDesc;
	}
	
}
