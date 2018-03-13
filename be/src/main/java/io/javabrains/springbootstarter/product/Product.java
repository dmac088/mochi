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
@PrimaryKeyJoinColumn(name = "product_id")
public class Product {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_id")
	private Long ProductID;
	
	@Transient
	private double rrp;
	
	private String ProductDesc;

	public Product (){
		
	}

	public Product (int id){
	
	}
	
	/*
	public double getRrp() {
		return this.rrp;
	}
	
	public void setRrp(double rrp) {
		this.rrp = rrp;
	}*/

	@Column(name="product_desc")
	public String getProductDesc() {
		return ProductDesc;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}
}
