package io.nzbee.entity.product.status;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductStatusEntity.class)
public abstract class ProductStatusEntity_ {

	public static volatile SingularAttribute<ProductStatusEntity, Long> productStatusId;
	public static volatile SingularAttribute<ProductStatusEntity, String> productStatusDesc;
	public static volatile SingularAttribute<ProductStatusEntity, String> productStatusCode;

	public static final String PRODUCT_STATUS_ID = "productStatusId";
	public static final String PRODUCT_STATUS_DESC = "productStatusDesc";
	public static final String PRODUCT_STATUS_CODE = "productStatusCode";

}

