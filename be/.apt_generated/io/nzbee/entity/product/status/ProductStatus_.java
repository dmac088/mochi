package io.nzbee.entity.product.status;

import io.nzbee.entity.product.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductStatus.class)
public abstract class ProductStatus_ {

	public static volatile SingularAttribute<ProductStatus, Long> productStatusId;
	public static volatile SingularAttribute<ProductStatus, String> productStatusDesc;
	public static volatile SingularAttribute<ProductStatus, String> productStatusCode;
	public static volatile ListAttribute<ProductStatus, Product> products;

}

