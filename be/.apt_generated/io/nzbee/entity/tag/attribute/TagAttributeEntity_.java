package io.nzbee.entity.tag.attribute;

import io.nzbee.entity.tag.TagEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TagAttributeEntity.class)
public abstract class TagAttributeEntity_ {

	public static volatile SingularAttribute<TagAttributeEntity, Long> tagAttributeId;
	public static volatile SingularAttribute<TagAttributeEntity, String> lclCd;
	public static volatile SingularAttribute<TagAttributeEntity, TagEntity> tag;
	public static volatile SingularAttribute<TagAttributeEntity, String> tagDesc;

	public static final String TAG_ATTRIBUTE_ID = "tagAttributeId";
	public static final String LCL_CD = "lclCd";
	public static final String TAG = "tag";
	public static final String TAG_DESC = "tagDesc";

}

