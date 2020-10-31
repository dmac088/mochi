package io.nzbee.entity.tag.attribute;

import io.nzbee.entity.tag.TagEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TagAttribute.class)
public abstract class TagAttribute_ {

	public static volatile SingularAttribute<TagAttribute, Long> tagAttributeId;
	public static volatile SingularAttribute<TagAttribute, String> lclCd;
	public static volatile SingularAttribute<TagAttribute, TagEntity> tag;
	public static volatile SingularAttribute<TagAttribute, String> tagDesc;

	public static final String TAG_ATTRIBUTE_ID = "tagAttributeId";
	public static final String LCL_CD = "lclCd";
	public static final String TAG = "tag";
	public static final String TAG_DESC = "tagDesc";

}

