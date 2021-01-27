package io.nzbee.entity.postage.type;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PostageType.class)
public abstract class PostageType_ {

	public static volatile SingularAttribute<PostageType, Long> postageTypeId;
	public static volatile SingularAttribute<PostageType, String> postageTypeCode;

	public static final String POSTAGE_TYPE_ID = "postageTypeId";
	public static final String POSTAGE_TYPE_CODE = "postageTypeCode";

}

