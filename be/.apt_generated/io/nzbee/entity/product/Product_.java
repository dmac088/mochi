package io.nzbee.entity.product;

import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.category.product.CategoryProduct;
import io.nzbee.entity.product.attribute.ProductAttribute;
import io.nzbee.entity.product.department.Department;
import io.nzbee.entity.product.price.ProductPrice;
import io.nzbee.entity.product.status.ProductStatus;
import io.nzbee.entity.tag.Tag;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, Long> productId;
	public static volatile SingularAttribute<Product, String> productUPC;
	public static volatile SingularAttribute<Product, ProductStatus> productStatus;
	public static volatile SingularAttribute<Product, Date> productCreateDt;
	public static volatile SetAttribute<Product, ProductAttribute> attributes;
	public static volatile SetAttribute<Product, CategoryProduct> categories;
	public static volatile SingularAttribute<Product, Department> department;
	public static volatile ListAttribute<Product, ProductPrice> prices;
	public static volatile SingularAttribute<Product, CategoryProduct> primaryCategoryIndex;
	public static volatile SingularAttribute<Product, Brand> brand;
	public static volatile SetAttribute<Product, Tag> tags;

	public static final String PRODUCT_ID = "productId";
	public static final String PRODUCT_UP_C = "productUPC";
	public static final String PRODUCT_STATUS = "productStatus";
	public static final String PRODUCT_CREATE_DT = "productCreateDt";
	public static final String ATTRIBUTES = "attributes";
	public static final String CATEGORIES = "categories";
	public static final String DEPARTMENT = "department";
	public static final String PRICES = "prices";
	public static final String PRIMARY_CATEGORY_INDEX = "primaryCategoryIndex";
	public static final String BRAND = "brand";
	public static final String TAGS = "tags";

}

