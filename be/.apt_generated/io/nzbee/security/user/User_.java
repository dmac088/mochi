package io.nzbee.security.user;

import io.nzbee.entity.party.Party;
import io.nzbee.security.user.role.UserRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Boolean> accountLocked;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, UserRole> roles;
	public static volatile SingularAttribute<User, Boolean> accountExpired;
	public static volatile SingularAttribute<User, Long> Id;
	public static volatile SingularAttribute<User, Party> userParty;
	public static volatile SingularAttribute<User, Boolean> isUsing2FA;
	public static volatile SingularAttribute<User, String> secret;
	public static volatile SingularAttribute<User, Boolean> credentialsExpired;
	public static volatile SingularAttribute<User, Boolean> enabled;
	public static volatile SingularAttribute<User, String> username;

	public static final String ACCOUNT_LOCKED = "accountLocked";
	public static final String PASSWORD = "password";
	public static final String ROLES = "roles";
	public static final String ACCOUNT_EXPIRED = "accountExpired";
	public static final String ID = "Id";
	public static final String USER_PARTY = "userParty";
	public static final String IS_USING2_FA = "isUsing2FA";
	public static final String SECRET = "secret";
	public static final String CREDENTIALS_EXPIRED = "credentialsExpired";
	public static final String ENABLED = "enabled";
	public static final String USERNAME = "username";

}

