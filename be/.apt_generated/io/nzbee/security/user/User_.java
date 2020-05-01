package io.nzbee.security.user;

import io.nzbee.entity.party.Party;
import io.nzbee.security.user.role.UserRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Boolean> accountLocked;
	public static volatile SingularAttribute<User, String> password;
	public static volatile ListAttribute<User, UserRole> roles;
	public static volatile SingularAttribute<User, Boolean> accountExpired;
	public static volatile SingularAttribute<User, Long> Id;
	public static volatile SingularAttribute<User, Party> userParty;
	public static volatile SingularAttribute<User, Boolean> credentialsExpired;
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SingularAttribute<User, String> username;

}

