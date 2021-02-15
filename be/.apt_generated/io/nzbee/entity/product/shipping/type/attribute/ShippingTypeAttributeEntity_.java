package io.nzbee.entity.product.shipping.type.attribute;

import io.nzbee.entity.product.shipping.type.ShippingTypeEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingTypeAttributeEntity.class)
public abstract class ShippingTypeAttributeEntity_ {

	public static volatile SingularAttribute<ShippingTypeAttributeEntity, String> lclCd;
	public static volatile SingularAttribute<ShippingTypeAttributeEntity, ShippingTypeEntity> shippingType;
	public static volatile SingularAttribute<ShippingTypeAttributeEntity, Long> Id;
	public static volatile SingularAttribute<ShippingTypeAttributeEntity, String> shippingTypeDesc;

	public static final String LCL_CD = "lclCd";
	public static final String SHIPPING_TYPE = "shippingType";
	public static final String ID = "Id";
	public static final String SHIPPING_TYPE_DESC = "shippingTypeDesc";

}

