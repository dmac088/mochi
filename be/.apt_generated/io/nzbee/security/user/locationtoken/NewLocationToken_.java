package io.nzbee.security.user.locationtoken;

import io.nzbee.security.user.location.UserLocation;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NewLocationToken.class)
public abstract class NewLocationToken_ {

	public static volatile SingularAttribute<NewLocationToken, UserLocation> userLocation;
	public static volatile SingularAttribute<NewLocationToken, Long> id;
	public static volatile SingularAttribute<NewLocationToken, String> token;

	public static final String USER_LOCATION = "userLocation";
	public static final String ID = "id";
	public static final String TOKEN = "token";

}

