package io.nzbee.entity.bag.status;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BagItemStatus.class)
public abstract class BagItemStatus_ {

	public static volatile SingularAttribute<BagItemStatus, String> bagStatusDesc;
	public static volatile SingularAttribute<BagItemStatus, Long> bagStatusId;
	public static volatile SingularAttribute<BagItemStatus, String> bagStatusCode;

	public static final String BAG_STATUS_DESC = "bagStatusDesc";
	public static final String BAG_STATUS_ID = "bagStatusId";
	public static final String BAG_STATUS_CODE = "bagStatusCode";

}

