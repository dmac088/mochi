package io.nzbee.entity.product.shipping.type;

import io.nzbee.entity.product.shipping.ShippingProductEntity;
import io.nzbee.entity.product.shipping.type.attribute.ShippingTypeAttributeEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingTypeEntity.class)
public abstract class ShippingTypeEntity_ {

	public static volatile SingularAttribute<ShippingTypeEntity, Long> shippingTypeId;
	public static volatile SingularAttribute<ShippingTypeEntity, String> shippingTypeCode;
	public static volatile ListAttribute<ShippingTypeEntity, ShippingTypeAttributeEntity> attributes;
	public static volatile SetAttribute<ShippingTypeEntity, ShippingProductEntity> products;

	public static final String SHIPPING_TYPE_ID = "shippingTypeId";
	public static final String SHIPPING_TYPE_CODE = "shippingTypeCode";
	public static final String ATTRIBUTES = "attributes";
	public static final String PRODUCTS = "products";

}

