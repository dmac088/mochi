package io.nzbee.entity.product.shipping;

import io.nzbee.entity.product.shipping.destination.ShippingDestinationEntity;
import io.nzbee.entity.product.shipping.type.ShippingTypeEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingProductEntity.class)
public abstract class ShippingProductEntity_ extends io.nzbee.entity.product.ProductEntity_ {

	public static volatile SingularAttribute<ShippingProductEntity, ShippingTypeEntity> postageType;
	public static volatile SingularAttribute<ShippingProductEntity, ShippingDestinationEntity> postageDestination;

	public static final String POSTAGE_TYPE = "postageType";
	public static final String POSTAGE_DESTINATION = "postageDestination";

}

