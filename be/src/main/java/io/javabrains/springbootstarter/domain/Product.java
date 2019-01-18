package io.javabrains.springbootstarter.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.search.annotations.IndexedEmbedded;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_id")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_id")
	private Long productId;

	@Column(name="upc_cd")
	private String productUPC;
	
	@Column(name="prd_crtd_dt")
	private Date productCreateDt;
	
	@ManyToMany(mappedBy = "products")
	@IndexedEmbedded
	@JsonIgnore
	private List<ProductCategory> categories;

	@OneToMany(mappedBy="product",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProductAttribute> productAttribute;

	public Long getProductId() {
		return productId;
	}
	
	public Collection<ProductCategory> getCategories() {
		return this.categories;
	}
	
	
	public List<ProductAttribute> getProductAttribute() {
		return productAttribute;
	}

	public void setProductAttribute(List<ProductAttribute> productAttribute) {
		this.productAttribute = productAttribute;
	}

	public void setCategories(List<ProductCategory> categories) {
		this.categories = categories;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductUPC() {
		return productUPC;
	}

	public void setProductUPC(String productUPC) {
		this.productUPC = productUPC;
	}

	public Date getProductCreateDt() {
		return productCreateDt;
	}

	public void setProductCreateDt(Date productCreateDt) {
		this.productCreateDt = productCreateDt;
	}
			
}
