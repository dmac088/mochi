package io.nzbee.security.user.devicemetadata;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DeviceMetadata.class)
public abstract class DeviceMetadata_ {

	public static volatile SingularAttribute<DeviceMetadata, String> deviceDetails;
	public static volatile SingularAttribute<DeviceMetadata, Date> lastLoggedIn;
	public static volatile SingularAttribute<DeviceMetadata, String> location;
	public static volatile SingularAttribute<DeviceMetadata, Long> id;
	public static volatile SingularAttribute<DeviceMetadata, Long> userId;

	public static final String DEVICE_DETAILS = "deviceDetails";
	public static final String LAST_LOGGED_IN = "lastLoggedIn";
	public static final String LOCATION = "location";
	public static final String ID = "id";
	public static final String USER_ID = "userId";

}

