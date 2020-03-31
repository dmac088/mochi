package io.nzbee.entity.product.food;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import io.nzbee.entity.product.Product;

@Entity
@Table(name = "product_food", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_id")
@DiscriminatorValue("3")
public class Food extends Product {

	public Food() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Column(name="exp_dt")
	private Date expiryDate;
	
	@Column(name="ctry_of_orig")
	private String countryOfOrigin;

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	
}
