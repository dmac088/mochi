package io.nzbee.entity.postage.type;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PostageTypeEntity.class)
public abstract class PostageSize_ {

	public static volatile SingularAttribute<PostageTypeEntity, Long> postageSizeId;
	public static volatile SingularAttribute<PostageTypeEntity, String> postageSizeCode;

	public static final String POSTAGE_SIZE_ID = "postageSizeId";
	public static final String POSTAGE_SIZE_CODE = "postageSizeCode";

}

