package io.nzbee.entity.bag.status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "bag_status", schema = "mochi")
public class BagStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bag_sts_id")
	private Long productStatusId;

	@Column(name="bag_sts_cd")
	@Field(store=Store.YES)
	private String productStatusCode;
	
	@Column(name="bag_sts_desc")
	@Field(store=Store.YES)
	private String productStatusDesc;

	public Long getProductStatusId() {
		return productStatusId;
	}

	public void setId(Long productStatusId) {
		this.productStatusId = productStatusId;
	}

	public String getCode() {
		return productStatusCode;
	}

	public void setCode(String productStatusCode) {
		this.productStatusCode = productStatusCode;
	}

	public String getDesc() {
		return productStatusDesc;
	}

	public void setDesc(String productStatusDesc) {
		this.productStatusDesc = productStatusDesc;
	}

}
