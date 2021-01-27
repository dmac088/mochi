package io.nzbee.entity.postage.destination;

import io.nzbee.entity.postage.zone.PostageZoneEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PostageDestinationEntity.class)
public abstract class PostageDestinationEntity_ {

	public static volatile SingularAttribute<PostageDestinationEntity, String> postageDestinationDesc;
	public static volatile SingularAttribute<PostageDestinationEntity, Long> postageDestinationId;
	public static volatile SingularAttribute<PostageDestinationEntity, PostageZoneEntity> postageZone;
	public static volatile SingularAttribute<PostageDestinationEntity, String> postageDestinationCode;

	public static final String POSTAGE_DESTINATION_DESC = "postageDestinationDesc";
	public static final String POSTAGE_DESTINATION_ID = "postageDestinationId";
	public static final String POSTAGE_ZONE = "postageZone";
	public static final String POSTAGE_DESTINATION_CODE = "postageDestinationCode";

}

