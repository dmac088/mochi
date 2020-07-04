package io.nzbee.entity.brand;

import io.nzbee.entity.brand.attribute.BrandAttribute;
import io.nzbee.entity.category.brand.CategoryBrand;
import io.nzbee.entity.product.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Brand.class)
public abstract class Brand_ {

	public static volatile SingularAttribute<Brand, Long> brandId;
	public static volatile ListAttribute<Brand, BrandAttribute> attributes;
	public static volatile ListAttribute<Brand, CategoryBrand> categories;
	public static volatile SingularAttribute<Brand, String> brandCode;
	public static volatile ListAttribute<Brand, Product> products;

	public static final String BRAND_ID = "brandId";
	public static final String ATTRIBUTES = "attributes";
	public static final String CATEGORIES = "categories";
	public static final String BRAND_CODE = "brandCode";
	public static final String PRODUCTS = "products";

}

