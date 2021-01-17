package io.nzbee.entity.product.status;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_status", schema = "mochi")
public class ProductStatusEntity implements Serializable {
	
	private static final long serialVersionUID = -3261140802795999392L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_sts_id")
	private Long productStatusId;

	@Column(name="prd_sts_cd")
	private String productStatusCode;
	
	@Column(name="prd_sts_desc")
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
