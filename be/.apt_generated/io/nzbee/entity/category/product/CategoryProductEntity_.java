package io.nzbee.entity.category.product;

import io.nzbee.entity.product.ProductEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryProductEntity.class)
public abstract class CategoryProductEntity_ extends io.nzbee.entity.category.CategoryEntity_ {

	public static volatile SetAttribute<CategoryProductEntity, ProductEntity> products;

	public static final String PRODUCTS = "products";

}

