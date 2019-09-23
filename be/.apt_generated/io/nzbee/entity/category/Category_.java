package io.nzbee.entity.category;

import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.product.hierarchy.Hierarchy;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, CategoryType> categoryType;
	public static volatile SingularAttribute<Category, Category> parent;
	public static volatile SingularAttribute<Category, Long> categoryLevel;
	public static volatile SingularAttribute<Category, CategoryAttribute> categoryAttribute;
	public static volatile SingularAttribute<Category, Hierarchy> hierarchy;
	public static volatile SingularAttribute<Category, Long> maxMarkdownPrice;
	public static volatile SingularAttribute<Category, String> categoryCode;
	public static volatile SingularAttribute<Category, Long> maxRetailPrice;
	public static volatile SingularAttribute<Category, Long> categoryId;

}

