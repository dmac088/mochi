package io.javabrains.springbootstarter.product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_id")
@SecondaryTable(name = "product_attr_lcl", pkJoinColumns = @PrimaryKeyJoinColumn(name = "prd_id"))
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_id")
	private Long productId;

	@Column(name="upc_cd")
	private String productUPC;
	
	@Column(name="prd_crtd_dt")
	private Date productCreateDt;
	
	@Column(name="prd_rrp", table="product_attr_lcl")
	private double ProductRrp;

	@Column(name="prd_desc", table="product_attr_lcl")
	private String ProductDesc;
	
	@Column(name="prd_img_pth", table="product_attr_lcl")
	private String ProductImage;
	
	@Column(name="prd_cat_desc", table="product_attr_lcl")
	private String ProductCategory;
	
	@Column(name="lcl_cd", table="product_attr_lcl")	
	private String lclCd;

	public Long getProductId() {
		return productId;
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


	public double getProductRrp() {
		return ProductRrp;
	}

	public void setProductRrp(double productRrp) {
		ProductRrp = productRrp;
	}

	public String getProductDesc() {
		return ProductDesc;
	}

	public void setProductDesc(String productDesc) {
		ProductDesc = productDesc;
	}

	public String getProductImage() {
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}

	public String getProductCategory() {
		return ProductCategory;
	}

	public void setProductCategory(String productCategory) {
		ProductCategory = productCategory;
	}

	public String getLclCd() {
		return lclCd;
	}
	
	public void setLclCd(String lclCd) {
		this.lclCd = lclCd;
	}
			
}
