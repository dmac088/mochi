package io.nzbee.entity.tag;

import io.nzbee.entity.tag.attribute.TagAttributeEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TagEntity.class)
public abstract class TagEntity_ {

	public static volatile SingularAttribute<TagEntity, String> tagCode;
	public static volatile SingularAttribute<TagEntity, Long> tagId;
	public static volatile SetAttribute<TagEntity, TagAttributeEntity> attributes;

	public static final String TAG_CODE = "tagCode";
	public static final String TAG_ID = "tagId";
	public static final String ATTRIBUTES = "attributes";

}

