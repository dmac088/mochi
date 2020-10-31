package io.nzbee.entity.category.product;

import io.nzbee.entity.product.ProductEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryProduct.class)
public abstract class CategoryProduct_ extends io.nzbee.entity.category.Category_ {

	public static volatile SetAttribute<CategoryProduct, ProductEntity> products;

	public static final String PRODUCTS = "products";

}

