package io.nzbee.entity.category.layout;

import io.nzbee.entity.category.Category;
import io.nzbee.entity.layout.Layout;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryLayout.class)
public abstract class CategoryLayout_ {

	public static volatile SingularAttribute<CategoryLayout, Layout> layout;
	public static volatile SingularAttribute<CategoryLayout, CategoryLayoutId> id;
	public static volatile SingularAttribute<CategoryLayout, Category> category;
	public static volatile SingularAttribute<CategoryLayout, Long> order;

}

