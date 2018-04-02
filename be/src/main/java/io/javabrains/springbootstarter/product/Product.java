package io.javabrains.springbootstarter.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "product")
@PrimaryKeyJoinColumn(name = "prd_id")
public class Product {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(value="id")
	@Column(name="prd_id")
	private Long ProductID;
	
	@Column(name="prd_rrp")
	@JsonProperty(value="price")
	private double rrp;

	@Column(name="prd_desc")
	@JsonProperty(value="name")
	private String ProductDesc;
	
	@Column(name="prd_img_pth")
	@JsonProperty(value="image")
	private String ProductImage;
	
	@Column(name="prd_cat_desc")
	private String ProductCategory;
	
	public String getcategory() {
		return ProductCategory;
	}

	public void setcategory(String productCategory) {
		ProductCategory = productCategory;
	}

	public Product (){
		
	}

	public Product (Long id){
	
	}
	
	public double getRrp() {
		return this.rrp;
	}
	
	
	public void setRrp(double rrp) {
		this.rrp = rrp;
	}

	public String getProductDesc() {
		return this.ProductDesc;
	}

	public void setProductDesc(String productDesc) {
		this.ProductDesc = productDesc;
	}
	
	public Long getProductID() {
		return ProductID;
	}

	public void setProductID(Long productID) {
		ProductID = productID;
	}

	public String getProductImage() {
		return ProductImage;
	}

	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}

}
