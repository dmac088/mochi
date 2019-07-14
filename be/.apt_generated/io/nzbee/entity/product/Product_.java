package io.nzbee.entity.product;

import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.tag.ProductTag;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, Long> productId;
	public static volatile SingularAttribute<Product, String> productUPC;
	public static volatile SingularAttribute<Product, ProductStatus> productStatus;
	public static volatile SingularAttribute<Product, Date> productCreateDt;
	public static volatile ListAttribute<Product, ProductAttribute> attributes;
	public static volatile ListAttribute<Product, Category> categories;
	public static volatile ListAttribute<Product, ProductPrice> prices;
	public static volatile SingularAttribute<Product, Brand> brand;
	public static volatile ListAttribute<Product, ProductTag> tags;

}

