package io.nzbee.entity.postage.destination;

import io.nzbee.entity.postage.zone.PostageZone;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PostageDestination.class)
public abstract class PostageDestination_ {

	public static volatile SingularAttribute<PostageDestination, String> postageDestinationDesc;
	public static volatile SingularAttribute<PostageDestination, Long> postageDestinationId;
	public static volatile SingularAttribute<PostageDestination, PostageZone> postageZone;
	public static volatile SingularAttribute<PostageDestination, String> postageDestinationCode;

	public static final String POSTAGE_DESTINATION_DESC = "postageDestinationDesc";
	public static final String POSTAGE_DESTINATION_ID = "postageDestinationId";
	public static final String POSTAGE_ZONE = "postageZone";
	public static final String POSTAGE_DESTINATION_CODE = "postageDestinationCode";

}

