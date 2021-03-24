package io.nzbee.entity.product.shipping.destination;

import io.nzbee.entity.product.shipping.destination.attribute.ShippingDestinationAttributeEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingDestinationEntity.class)
public abstract class ShippingDestinationEntity_ {

	public static volatile SingularAttribute<ShippingDestinationEntity, String> shippingDestinationDesc;
	public static volatile SingularAttribute<ShippingDestinationEntity, String> shippingDestinationZoneCode;
	public static volatile SingularAttribute<ShippingDestinationEntity, String> shippingDestinationShortCode;
	public static volatile SingularAttribute<ShippingDestinationEntity, String> shippingDestinationActive;
	public static volatile ListAttribute<ShippingDestinationEntity, ShippingDestinationAttributeEntity> attributes;
	public static volatile SingularAttribute<ShippingDestinationEntity, Long> shippingDestinationId;
	public static volatile SingularAttribute<ShippingDestinationEntity, String> shippingDestinationCode;

	public static final String SHIPPING_DESTINATION_DESC = "shippingDestinationDesc";
	public static final String SHIPPING_DESTINATION_ZONE_CODE = "shippingDestinationZoneCode";
	public static final String SHIPPING_DESTINATION_SHORT_CODE = "shippingDestinationShortCode";
	public static final String SHIPPING_DESTINATION_ACTIVE = "shippingDestinationActive";
	public static final String ATTRIBUTES = "attributes";
	public static final String SHIPPING_DESTINATION_ID = "shippingDestinationId";
	public static final String SHIPPING_DESTINATION_CODE = "shippingDestinationCode";

}

