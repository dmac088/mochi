package io.nzbee.entity.stock;

import io.nzbee.entity.product.ProductEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StockOnHand.class)
public abstract class StockOnHand_ {

	public static volatile SingularAttribute<StockOnHand, ProductEntity> product;
	public static volatile SingularAttribute<StockOnHand, Long> stockOnHandId;
	public static volatile SingularAttribute<StockOnHand, Long> stockOnHand;

	public static final String PRODUCT = "product";
	public static final String STOCK_ON_HAND_ID = "stockOnHandId";
	public static final String STOCK_ON_HAND = "stockOnHand";

}

