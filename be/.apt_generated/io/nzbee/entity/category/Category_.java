package io.nzbee.entity.category;

import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.type.CategoryType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, String> categoryParentCode;
	public static volatile SingularAttribute<Category, CategoryType> categoryType;
	public static volatile SingularAttribute<Category, Long> categoryParentId;
	public static volatile SingularAttribute<Category, Category> parent;
	public static volatile SingularAttribute<Category, Long> categoryLevel;
	public static volatile SetAttribute<Category, CategoryAttribute> attributes;
	public static volatile SingularAttribute<Category, String> categoryCode;
	public static volatile SingularAttribute<Category, Long> categoryId;

	public static final String CATEGORY_PARENT_CODE = "categoryParentCode";
	public static final String CATEGORY_TYPE = "categoryType";
	public static final String CATEGORY_PARENT_ID = "categoryParentId";
	public static final String PARENT = "parent";
	public static final String CATEGORY_LEVEL = "categoryLevel";
	public static final String ATTRIBUTES = "attributes";
	public static final String CATEGORY_CODE = "categoryCode";
	public static final String CATEGORY_ID = "categoryId";

}

