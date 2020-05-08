package io.nzbee.entity.layout;

import io.nzbee.entity.category.layout.CategoryLayout;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Layout.class)
public abstract class Layout_ {

	public static volatile SetAttribute<Layout, CategoryLayout> categoryLayouts;
	public static volatile SingularAttribute<Layout, String> code;
	public static volatile SingularAttribute<Layout, Long> layoutId;
	public static volatile SingularAttribute<Layout, String> desc;

}

