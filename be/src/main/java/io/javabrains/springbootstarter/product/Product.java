package io.javabrains.springbootstarter.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product")
@PrimaryKeyJoinColumn(name = "prd_id")
public class Product {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_id")
	private Long ProductId;
	
	@Column(name="prd_rrp")
	private double ProductRrp;

	@Column(name="prd_desc")
	private String ProductDesc;
	
	@Column(name="prd_img_pth")
	private String ProductImage;
	
	@Column(name="prd_cat_desc")
	private String ProductCategory;
	
	@Transient
	private String ProductQty;

	public String getcategory() {
		return ProductCategory;
	}

	public void setcategory(String productCategory) {
		ProductCategory = productCategory;
	}

	public Product (){
		this.ProductQty = "0";
	}

	public Product (Long id){
		this.ProductQty = "0";
	}
	
	public double getProductRrp() {
		return this.ProductRrp;
	}
	
	
	public void setProductRrp(double rrp) {
		this.ProductRrp = rrp;
	}

	public String getProductDesc() {
		return this.ProductDesc;
	}

	public void setProductDesc(String productDesc) {
		this.ProductDesc = productDesc;
	}
	
	public Long getProductId() {
		return ProductId;
	}

	public void setProductID(Long productId) {
		ProductId = productId;
	}

	public String getProductImage() {
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}

	public String getProductQty() {
		return ProductQty;
	}

	public void setProductQty(String productQty) {
		ProductQty = productQty;
	}
	
}
