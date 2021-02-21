package io.nzbee.entity.product.shipping;

import io.nzbee.entity.product.shipping.destination.ShippingDestinationEntity;
import io.nzbee.entity.product.shipping.type.ShippingTypeEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingProductEntity.class)
public abstract class ShippingProductEntity_ extends io.nzbee.entity.product.ProductEntity_ {

	public static volatile SingularAttribute<ShippingProductEntity, ShippingDestinationEntity> shippingDestination;
	public static volatile SingularAttribute<ShippingProductEntity, ShippingTypeEntity> shippingType;
	public static volatile SingularAttribute<ShippingProductEntity, Double> weightTo;
	public static volatile SingularAttribute<ShippingProductEntity, Double> weightLimit;
	public static volatile SingularAttribute<ShippingProductEntity, Double> weightFrom;

	public static final String SHIPPING_DESTINATION = "shippingDestination";
	public static final String SHIPPING_TYPE = "shippingType";
	public static final String WEIGHT_TO = "weightTo";
	public static final String WEIGHT_LIMIT = "weightLimit";
	public static final String WEIGHT_FROM = "weightFrom";

}

