package io.nzbee.entity.bag.status;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BagStatus.class)
public abstract class BagStatus_ {

	public static volatile SingularAttribute<BagStatus, Long> productStatusId;
	public static volatile SingularAttribute<BagStatus, String> productStatusDesc;
	public static volatile SingularAttribute<BagStatus, String> productStatusCode;

	public static final String PRODUCT_STATUS_ID = "productStatusId";
	public static final String PRODUCT_STATUS_DESC = "productStatusDesc";
	public static final String PRODUCT_STATUS_CODE = "productStatusCode";

}

