package io.nzbee.entity.product.food;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import io.nzbee.entity.product.Product;

@Indexed
@Entity
@Table(name = "product_food", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_id")
@DiscriminatorValue("3")
public class Food extends Product {

	@Column(name="exp_dt")
	private Date expiryDate;
	
	@Column(name="ctry_of_orig")
	@Field(analyze = Analyze.NO, store=Store.YES)
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
