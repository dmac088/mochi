package io.nzbee.entity.product.shipping.destination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.nzbee.entity.product.shipping.ShippingProductEntity;
import io.nzbee.entity.product.shipping.destination.attribute.ShippingDestinationAttributeEntity;

@Entity
@Table(name="shipping_destination")
public class ShippingDestinationEntity implements Serializable {

	private static final long serialVersionUID = 6452388121663774849L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="shp_dst_id")
	private Long shippingDestinationId;
	
	@Column(name="shp_dst_cd")
	private String shippingDestinationCode;
	
	@Column(name="shp_dst_sht_cd")
	private String shippingDestinationShortCode; 
	
	@Column(name="shp_dst_desc")
	private String shippingDestinationDesc;
	
	@Column(name="shp_zne_cd")
	private String shippingDestinationZoneCode;
	
	@Column(name="shp_dst_act")
	private String shippingDestinationActive;
	
	@OneToMany(	mappedBy="shippingDestination",  
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private List<ShippingDestinationAttributeEntity> attributes = new ArrayList<ShippingDestinationAttributeEntity>();

	@OneToMany(	mappedBy="shippingDestination",
				cascade = CascadeType.ALL,
				orphanRemoval = true)
	private Set<ShippingProductEntity> products = new HashSet<ShippingProductEntity>();
	
	public Long getShippingDestinationId() {
		return shippingDestinationId;
	}

	public String getShippingDestinationCode() {
		return shippingDestinationCode;
	}
	
	public void setShippingDestinationCode(String shippingDestinationCode) {
		this.shippingDestinationCode = shippingDestinationCode;
	}
	
	public String getShippingDestinationShortCode() {
		return shippingDestinationShortCode;
	}

	public void setShippingDestinationShortCode(String shippingDestinationShortCode) {
		this.shippingDestinationShortCode = shippingDestinationShortCode;
	}

	public String getShippingDestinationDesc() {
		return shippingDestinationDesc;
	}

	public void setShippingDestinationDesc(String shippingDestinationDesc) {
		this.shippingDestinationDesc = shippingDestinationDesc;
	}

	public String getShippingZoneCode() {
		return shippingDestinationZoneCode;
	}

	public void setShippingDestinationZoneCode(String shippingDestinationZoneCode) {
		this.shippingDestinationZoneCode = shippingDestinationZoneCode;
	}

	public List<ShippingDestinationAttributeEntity> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ShippingDestinationAttributeEntity> attributes) {
		this.attributes = attributes;
	}

	public Boolean getShippingDestinationActive() {
		return shippingDestinationActive.equals("Y");
	}

	public void setShippingDestinationActive(String shippingDestinationActive) {
		this.shippingDestinationActive = shippingDestinationActive;
	}

	public Set<ShippingProductEntity> getProducts() {
		return products;
	}

	public void setProducts(Set<ShippingProductEntity> products) {
		this.products = products;
	}

}
