package io.nzbee.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Hierarchy.class)
public abstract class Hierarchy_ {

	public static volatile SingularAttribute<Hierarchy, String> code;
	public static volatile SingularAttribute<Hierarchy, Long> hierarchyId;
	public static volatile ListAttribute<Hierarchy, Category> categories;
	public static volatile SingularAttribute<Hierarchy, String> desc;

}

