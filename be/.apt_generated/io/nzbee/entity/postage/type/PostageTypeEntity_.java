package io.nzbee.entity.postage.type;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import io.nzbee.entity.product.shipping.type.PostageTypeEntity;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PostageTypeEntity.class)
public abstract class PostageTypeEntity_ {

	public static volatile SingularAttribute<PostageTypeEntity, Long> postageTypeId;
	public static volatile SingularAttribute<PostageTypeEntity, String> postageTypeCode;

	public static final String POSTAGE_TYPE_ID = "postageTypeId";
	public static final String POSTAGE_TYPE_CODE = "postageTypeCode";

}

