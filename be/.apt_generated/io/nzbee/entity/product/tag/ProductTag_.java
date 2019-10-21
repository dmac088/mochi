package io.nzbee.entity.product.tag;

import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.tag.attribute.ProductTagAttribute;
import io.nzbee.entity.tag.Tag;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tag.class)
public abstract class ProductTag_ {

	public static volatile SingularAttribute<Tag, String> productTagCode;
	public static volatile SingularAttribute<Tag, Long> productTagId;
	public static volatile ListAttribute<Tag, ProductTagAttribute> attributes;
	public static volatile SetAttribute<Tag, Product> products;

}

