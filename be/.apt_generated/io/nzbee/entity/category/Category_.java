package io.nzbee.entity.category;

import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.hierarchy.Hierarchy;
import io.nzbee.entity.layout.Layout;
import io.nzbee.entity.product.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, CategoryType> categoryType;
	public static volatile SingularAttribute<Category, Category> parent;
	public static volatile ListAttribute<Category, Brand> brands;
	public static volatile ListAttribute<Category, Category> children;
	public static volatile SingularAttribute<Category, Long> categoryLevel;
	public static volatile SingularAttribute<Category, Hierarchy> hierarchy;
	public static volatile ListAttribute<Category, CategoryAttribute> attributes;
	public static volatile SingularAttribute<Category, String> categoryCode;
	public static volatile ListAttribute<Category, Layout> layouts;
	public static volatile SingularAttribute<Category, Long> categoryId;
	public static volatile ListAttribute<Category, Product> products;

}

