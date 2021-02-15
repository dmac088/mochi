package io.nzbee.entity.product.shipping.destination.attribute;

import io.nzbee.entity.product.shipping.destination.ShippingDestinationEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingDestinationAttributeEntity.class)
public abstract class ShippingDestinationAttributeEntity_ {

	public static volatile SingularAttribute<ShippingDestinationAttributeEntity, String> shippingDestinationDesc;
	public static volatile SingularAttribute<ShippingDestinationAttributeEntity, String> lclCd;
	public static volatile SingularAttribute<ShippingDestinationAttributeEntity, ShippingDestinationEntity> shippingDestination;
	public static volatile SingularAttribute<ShippingDestinationAttributeEntity, Long> Id;

	public static final String SHIPPING_DESTINATION_DESC = "shippingDestinationDesc";
	public static final String LCL_CD = "lclCd";
	public static final String SHIPPING_DESTINATION = "shippingDestination";
	public static final String ID = "Id";

}

