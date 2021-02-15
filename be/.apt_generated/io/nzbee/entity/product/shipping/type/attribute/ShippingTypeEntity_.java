package io.nzbee.entity.product.shipping.type.attribute;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingTypeAttributeEntity.class)
public abstract class ShippingTypeEntity_ {

	public static volatile SingularAttribute<ShippingTypeAttributeEntity, Long> postageTypeId;
	public static volatile SingularAttribute<ShippingTypeAttributeEntity, String> postageTypeCode;

	public static final String POSTAGE_TYPE_ID = "postageTypeId";
	public static final String POSTAGE_TYPE_CODE = "postageTypeCode";

}

