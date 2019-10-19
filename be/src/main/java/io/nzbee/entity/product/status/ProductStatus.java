package io.nzbee.entity.product.status;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.nzbee.entity.product.Product;

@Entity
@Table(name = "product_status", schema = "mochi")
public class ProductStatus {
	
	@Id
	@Column(name="prd_sts_id")
	private Long productStatusId;

	@Column(name="prd_sts_cd")
	@Field(store=Store.NO)
	private String productStatusCode;
	
	@Column(name="prd_sts_desc")
	@Field(store=Store.NO)
	private String productStatusDesc;
	
	@OneToMany(	mappedBy="productStatus", 
				cascade = CascadeType.ALL,
				orphanRemoval = true)
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
