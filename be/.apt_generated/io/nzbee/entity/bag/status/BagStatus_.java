package io.nzbee.entity.bag.status;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BagStatus.class)
public abstract class BagStatus_ {

	public static volatile SingularAttribute<BagStatus, String> bagStatusDesc;
	public static volatile SingularAttribute<BagStatus, Long> bagStatusId;
	public static volatile SingularAttribute<BagStatus, String> bagStatusCode;

	public static final String BAG_STATUS_DESC = "bagStatusDesc";
	public static final String BAG_STATUS_ID = "bagStatusId";
	public static final String BAG_STATUS_CODE = "bagStatusCode";

}

