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

}

