package io.nzbee.entity.product.shipping;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.shipping.destination.ShippingDestinationEntity;
import io.nzbee.entity.product.shipping.type.ShippingTypeEntity;

@Entity
@Table(name = "product_shipping", schema = "mochi")
@DiscriminatorValue("1")
public class ShippingProductEntity extends ProductEntity {

	private static final long serialVersionUID = -2982247105587439319L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="shp_typ_id")
	private ShippingTypeEntity shippingType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="shp_dst_id")
	private ShippingDestinationEntity shippingDestination;

	@Column(name="shp_wgt_lim")
	private Double weightLimit;
	
	@Column(name="shp_wgt_frm")
	private Double weightFrom;
	
	@Column(name="shp_wgt_to")
	private Double weightTo;

	public ShippingTypeEntity getShippingType() {
		return shippingType;
	}

	public void setShippingType(ShippingTypeEntity shippingType) {
		this.shippingType = shippingType;
	}

	public ShippingDestinationEntity getShippingDestination() {
		return shippingDestination;
	}

	public void setShippingDestination(ShippingDestinationEntity shippingDestination) {
		this.shippingDestination = shippingDestination;
	}

	public Double getWeightLimit() {
		return weightLimit;
	}

	public void setWeightLimit(Double weightLimit) {
		this.weightLimit = weightLimit;
	}

	public Double getWeightFrom() {
		return weightFrom;
	}

	public void setWeightFrom(Double weightFrom) {
		this.weightFrom = weightFrom;
	}

	public Double getWeightTo() {
		return weightTo;
	}

	public void setWeightTo(Double weightTo) {
		this.weightTo = weightTo;
	}

}
