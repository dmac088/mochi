package io.nzbee.security;

import io.nzbee.entity.Party;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Boolean> accountLocked;
	public static volatile SingularAttribute<User, String> password;
	public static volatile CollectionAttribute<User, UserRole> roles;
	public static volatile SingularAttribute<User, Boolean> accountExpired;
	public static volatile SingularAttribute<User, Long> Id;
	public static volatile SingularAttribute<User, Party> userParty;
	public static volatile SingularAttribute<User, Boolean> credentialsExpired;
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SingularAttribute<User, String> username;

}

