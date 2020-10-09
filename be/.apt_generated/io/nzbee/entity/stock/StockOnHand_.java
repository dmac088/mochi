package io.nzbee.entity.stock;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StockOnHand.class)
public abstract class StockOnHand_ {

	public static volatile SingularAttribute<StockOnHand, Long> productId;
	public static volatile SingularAttribute<StockOnHand, Long> stockOnHandId;
	public static volatile SingularAttribute<StockOnHand, Long> stockOnHand;

	public static final String PRODUCT_ID = "productId";
	public static final String STOCK_ON_HAND_ID = "stockOnHandId";
	public static final String STOCK_ON_HAND = "stockOnHand";

}

