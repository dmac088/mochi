package io.nzbee.entity.product.tag.attribute;

import io.nzbee.entity.product.tag.ProductTag;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductTagAttribute.class)
public abstract class ProductTagAttribute_ {

	public static volatile SingularAttribute<ProductTagAttribute, Long> tagId;
	public static volatile SingularAttribute<ProductTagAttribute, String> lclCd;
	public static volatile SingularAttribute<ProductTagAttribute, Long> Id;
	public static volatile SingularAttribute<ProductTagAttribute, ProductTag> tag;
	public static volatile SingularAttribute<ProductTagAttribute, String> tagDesc;

}

