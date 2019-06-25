package io.nzbee.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product_status", schema = "mochi")
public class ProductStatus {
	
	@Id
	@Column(name="prd_sts_id")
	private Long productStatusId;

	@Column(name="prd_sts_cd")
	private String productStatusCode;
	
	@Column(name="prd_sts_desc")
	private String productStatusDesc;
	
	@OneToMany(mappedBy="productStatus", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Product> products;

	public Long getProductStatusId() {
		return productStatusId;
	}

	public void setProductStatusId(Long productStatusId) {
		this.productStatusId = productStatusId;
	}

	public String getProductStatusCode() {
		return productStatusCode;
	}

	public void setProductStatusCode(String productStatusCode) {
		this.productStatusCode = productStatusCode;
	}

	public String getProductStatusDesc() {
		return productStatusDesc;
	}

	public void setProductStatusDesc(String productStatusDesc) {
		this.productStatusDesc = productStatusDesc;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
