package io.nzbee.entity.category;

import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.layout.CategoryLayout;
import io.nzbee.entity.category.type.CategoryType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, CategoryType> categoryType;
	public static volatile SingularAttribute<Category, Category> parent;
	public static volatile SingularAttribute<Category, Long> categoryLevel;
	public static volatile SetAttribute<Category, CategoryAttribute> attributes;
	public static volatile SingularAttribute<Category, String> categoryCode;
	public static volatile SetAttribute<Category, CategoryLayout> layoutCategories;
	public static volatile SingularAttribute<Category, Long> categoryId;

}

