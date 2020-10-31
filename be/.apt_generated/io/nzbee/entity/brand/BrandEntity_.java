package io.nzbee.entity.brand;

import io.nzbee.entity.brand.attribute.BrandAttributeEntity;
import io.nzbee.entity.category.brand.CategoryBrandEntity;
import io.nzbee.entity.product.ProductEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BrandEntity.class)
public abstract class BrandEntity_ {

	public static volatile SingularAttribute<BrandEntity, Long> brandId;
	public static volatile SetAttribute<BrandEntity, BrandAttributeEntity> attributes;
	public static volatile SetAttribute<BrandEntity, CategoryBrandEntity> categories;
	public static volatile SingularAttribute<BrandEntity, String> brandCode;
	public static volatile SetAttribute<BrandEntity, ProductEntity> products;

	public static final String BRAND_ID = "brandId";
	public static final String ATTRIBUTES = "attributes";
	public static final String CATEGORIES = "categories";
	public static final String BRAND_CODE = "brandCode";
	public static final String PRODUCTS = "products";

}

