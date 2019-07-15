package io.nzbee.security;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserRole.class)
public abstract class UserRole_ {

	public static volatile SingularAttribute<UserRole, String> name;
	public static volatile CollectionAttribute<UserRole, User> Users;
	public static volatile SingularAttribute<UserRole, Long> Id;
	public static volatile CollectionAttribute<UserRole, Authority> authorities;

}

