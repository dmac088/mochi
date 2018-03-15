package io.javabrains.springbootstarter.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "product")
@PrimaryKeyJoinColumn(name = "prd_id")
public class Product {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prd_id")
	private Long ProductID;
	
	@Column(name="prd_rrp")
	private double rrp;

	@Column(name="prd_desc")
	private String ProductDesc;
	
	@Column(name="prd_img_pth")
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
	
	public double getprice() {
		return this.rrp;
	}
	
	public void setprice(double rrp) {
		this.rrp = rrp;
	}

	public String getname() {
		return this.ProductDesc;
	}

	public void setname(String productDesc) {
		this.ProductDesc = productDesc;
	}
	
	public Long getid() {
		return ProductID;
	}

	public void setid(Long productID) {
		ProductID = productID;
	}

	public String getimage() {
		return ProductImage;
	}

	public void setimage(String productImage) {
		ProductImage = productImage;
	}

}
