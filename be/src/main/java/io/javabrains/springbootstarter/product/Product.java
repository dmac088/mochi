package io.javabrains.springbootstarter.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@PrimaryKeyJoinColumn(name = "product_id")
public class Product {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_id")
	private Long ProductID;
	
	private double rrp;
	private String ProductName;

	public Product (){
		
	}

	public Product (int id){
		//super(id);
	}
	
	public double getRrp() {
		return this.rrp;
	}
	
	public void setRrp(double rrp) {
		this.rrp = rrp;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}
}
