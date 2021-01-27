package io.nzbee.entity.product.shipping;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import io.nzbee.entity.postage.customs.CustomsFormEntity;
import io.nzbee.entity.postage.destination.PostageDestinationEntity;
import io.nzbee.entity.postage.insurance.PostageInsuranceEntity;
import io.nzbee.entity.postage.size.PostageSizeEntity;
import io.nzbee.entity.postage.type.PostageTypeEntity;
import io.nzbee.entity.postage.zone.PostageZoneEntity;
import io.nzbee.entity.product.ProductEntity;

@Entity
@Table(name = "product_shipping", schema = "mochi")
@DiscriminatorValue("1")
public class ShippingProductEntity extends ProductEntity {

	private static final long serialVersionUID = -5894324320785215250L;

	@Column(name="country_cd")
	private String countryCode;
	
	@Column(name="country_name")
	private String countryName;
	
	@Column(name="city_name")
	private String cityName;
	
	@Column(name="post_cd")
	private String postCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pst_zne_id")
	private PostageZoneEntity postageZone;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pst_typ_id")
	private PostageTypeEntity postageType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pst_cst_id")
	private CustomsFormEntity customsForm;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pst_dst_id")
	private PostageDestinationEntity postageDestination;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pst_siz_id")
	private PostageSizeEntity postageSize;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pst_sins_id")
	private PostageInsuranceEntity postageInsurance;
	
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

	public PostageZoneEntity getPostageZone() {
		return postageZone;
	}

	public void setPostageZone(PostageZoneEntity postageZone) {
		this.postageZone = postageZone;
	}

	public PostageTypeEntity getPostageType() {
		return postageType;
	}

	public void setPostageType(PostageTypeEntity postageType) {
		this.postageType = postageType;
	}

	public CustomsFormEntity getCustomsForm() {
		return customsForm;
	}

	public void setCustomsForm(CustomsFormEntity customsForm) {
		this.customsForm = customsForm;
	}

	public PostageDestinationEntity getPostageDestination() {
		return postageDestination;
	}

	public void setPostageDestination(PostageDestinationEntity postageDestination) {
		this.postageDestination = postageDestination;
	}

	public PostageSizeEntity getPostageSize() {
		return postageSize;
	}

	public void setPostageSize(PostageSizeEntity postageSize) {
		this.postageSize = postageSize;
	}

	public PostageInsuranceEntity getPostageInsurance() {
		return postageInsurance;
	}

	public void setPostageInsurance(PostageInsuranceEntity postageInsurance) {
		this.postageInsurance = postageInsurance;
	}
	
}
