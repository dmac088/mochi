package io.nzbee.entity.product.status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

@Entity
@Table(name = "product_status", schema = "mochi")
public class ProductStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_sts_id")
	private Long productStatusId;

	@Column(name="prd_sts_cd")
	@Field(store=Store.NO)
	private String code;
	
	@Column(name="prd_sts_desc")
	@Field(store=Store.NO)
	private String desc;

	public Long getProductStatusId() {
		return productStatusId;
	}

	public void setId(Long productStatusId) {
		this.productStatusId = productStatusId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String productStatusCode) {
		this.code = productStatusCode;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String productStatusDesc) {
		this.desc = productStatusDesc;
	}

}
