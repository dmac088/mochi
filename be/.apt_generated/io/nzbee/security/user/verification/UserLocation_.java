package io.nzbee.security.user.verification;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserLocation.class)
public abstract class UserLocation_ {

	public static volatile SingularAttribute<UserLocation, String> country;
	public static volatile SingularAttribute<UserLocation, Long> id;
	public static volatile SingularAttribute<UserLocation, Boolean> enabled;

	public static final String COUNTRY = "country";
	public static final String ID = "id";
	public static final String ENABLED = "enabled";

}

