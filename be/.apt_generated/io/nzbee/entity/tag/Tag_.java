package io.nzbee.entity.tag;

import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.tag.attribute.TagAttribute;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tag.class)
public abstract class Tag_ {

	public static volatile SingularAttribute<Tag, String> tagCode;
	public static volatile SingularAttribute<Tag, Long> tagId;
	public static volatile SetAttribute<Tag, TagAttribute> attributes;
	public static volatile SetAttribute<Tag, ProductEntity> products;

	public static final String TAG_CODE = "tagCode";
	public static final String TAG_ID = "tagId";
	public static final String ATTRIBUTES = "attributes";
	public static final String PRODUCTS = "products";

}

