package io.nzbee.entity.product.tag;

import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.tag.attribute.ProductTagAttribute;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductTag.class)
public abstract class ProductTag_ {

	public static volatile SingularAttribute<ProductTag, String> productTagCode;
	public static volatile SingularAttribute<ProductTag, Long> productTagId;
	public static volatile ListAttribute<ProductTag, ProductTagAttribute> attributes;
	public static volatile ListAttribute<ProductTag, Product> products;

}

