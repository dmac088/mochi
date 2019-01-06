package io.javabrains.springbootstarter.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_id")
@SecondaryTable(schema = "mochi", name = "product_attr_lcl", pkJoinColumns = @PrimaryKeyJoinColumn(name = "prd_id"))
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
	@JsonIgnore
	private Collection<ProductCategory> categories;

	public Long getProductId() {
		return productId;
	}
	
	public Collection<ProductCategory> getCategories() {
		return this.categories;
	}

	public void setCategories(Collection<ProductCategory> categories) {
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
