package io.nzbee.entity.product.shipping.type;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingTypeEntity.class)
public abstract class PostageTypeEntity_ {

	public static volatile SingularAttribute<ShippingTypeEntity, Long> postageTypeId;
	public static volatile SingularAttribute<ShippingTypeEntity, String> postageTypeCode;

	public static final String POSTAGE_TYPE_ID = "postageTypeId";
	public static final String POSTAGE_TYPE_CODE = "postageTypeCode";

}

