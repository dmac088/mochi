package io.nzbee.entity.product;

import io.nzbee.entity.brand.BrandEntity;
import io.nzbee.entity.category.product.CategoryProductEntity;
import io.nzbee.entity.product.attribute.ProductAttributeEntity;
import io.nzbee.entity.product.department.DepartmentEntity;
import io.nzbee.entity.product.price.ProductPriceEntity;
import io.nzbee.entity.product.status.ProductStatusEntity;
import io.nzbee.entity.tag.TagEntity;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductEntity.class)
public abstract class ProductEntity_ {

	public static volatile SingularAttribute<ProductEntity, Long> productId;
	public static volatile SingularAttribute<ProductEntity, String> productUPC;
	public static volatile SingularAttribute<ProductEntity, ProductStatusEntity> productStatus;
	public static volatile SingularAttribute<ProductEntity, LocalDateTime> productCreateDt;
	public static volatile SetAttribute<ProductEntity, ProductAttributeEntity> attributes;
	public static volatile SetAttribute<ProductEntity, CategoryProductEntity> categories;
	public static volatile SingularAttribute<ProductEntity, DepartmentEntity> department;
	public static volatile SetAttribute<ProductEntity, ProductPriceEntity> prices;
	public static volatile SingularAttribute<ProductEntity, BrandEntity> brand;
	public static volatile SetAttribute<ProductEntity, TagEntity> tags;

	public static final String PRODUCT_ID = "productId";
	public static final String PRODUCT_UP_C = "productUPC";
	public static final String PRODUCT_STATUS = "productStatus";
	public static final String PRODUCT_CREATE_DT = "productCreateDt";
	public static final String ATTRIBUTES = "attributes";
	public static final String CATEGORIES = "categories";
	public static final String DEPARTMENT = "department";
	public static final String PRICES = "prices";
	public static final String BRAND = "brand";
	public static final String TAGS = "tags";

}

