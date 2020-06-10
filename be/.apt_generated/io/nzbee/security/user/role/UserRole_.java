package io.nzbee.security.user.role;

import io.nzbee.security.authority.Authority;
import io.nzbee.security.user.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserRole.class)
public abstract class UserRole_ {

	public static volatile SingularAttribute<UserRole, String> name;
	public static volatile SetAttribute<UserRole, User> Users;
	public static volatile SingularAttribute<UserRole, Long> Id;
	public static volatile SetAttribute<UserRole, Authority> authorities;

}

