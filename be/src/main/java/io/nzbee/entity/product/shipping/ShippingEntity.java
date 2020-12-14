package io.nzbee.entity.product.shipping;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

import io.nzbee.entity.product.ProductEntity;

@Indexed
@Entity
@Table(name = "product_shipping", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_id")
@DiscriminatorValue("1")
public class ShippingEntity extends ProductEntity {

	@Column(name="country_cd")
	private String countryCode;
	
	@Column(name="country_name")
	private String countryName;
	
	@Column(name="city_name")
	private String cityName;
	
	@Column(name="post_cd")
	private String postCode;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	
}
