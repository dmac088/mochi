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

}
