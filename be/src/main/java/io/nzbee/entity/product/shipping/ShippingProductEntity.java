package io.nzbee.entity.product.shipping;

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

	private static final long serialVersionUID = -5894324320785215250L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pst_typ_id")
	private ShippingTypeEntity postageType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pst_dst_id")
	private ShippingDestinationEntity postageDestination;


	public ShippingTypeEntity getPostageType() {
		return postageType;
	}

	public void setPostageType(ShippingTypeEntity postageType) {
		this.postageType = postageType;
	}

	public ShippingDestinationEntity getPostageDestination() {
		return postageDestination;
	}

	public void setPostageDestination(ShippingDestinationEntity postageDestination) {
		this.postageDestination = postageDestination;
	}

}
