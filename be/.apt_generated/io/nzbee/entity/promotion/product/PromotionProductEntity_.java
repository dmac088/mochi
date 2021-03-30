package io.nzbee.entity.promotion.product;

import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.product.ProductEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionProductEntity.class)
public abstract class PromotionProductEntity_ extends io.nzbee.entity.promotion.PromotionEntity_ {

	public static volatile SetAttribute<PromotionProductEntity, CategoryEntity> categories;
	public static volatile SetAttribute<PromotionProductEntity, ProductEntity> products;

	public static final String CATEGORIES = "categories";
	public static final String PRODUCTS = "products";

}

