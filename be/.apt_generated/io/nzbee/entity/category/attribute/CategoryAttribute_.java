package io.nzbee.entity.category.attribute;

import io.nzbee.entity.category.Category;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryAttribute.class)
public abstract class CategoryAttribute_ {

	public static volatile SingularAttribute<CategoryAttribute, String> lclCd;
	public static volatile SingularAttribute<CategoryAttribute, Long> categoryAttributeId;
	public static volatile SingularAttribute<CategoryAttribute, Category> category;
	public static volatile SingularAttribute<CategoryAttribute, String> categoryDesc;

	public static final String LCL_CD = "lclCd";
	public static final String CATEGORY_ATTRIBUTE_ID = "categoryAttributeId";
	public static final String CATEGORY = "category";
	public static final String CATEGORY_DESC = "categoryDesc";

}

