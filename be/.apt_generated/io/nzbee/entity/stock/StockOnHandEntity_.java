package io.nzbee.entity.stock;

import io.nzbee.entity.product.physical.PhysicalProductEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StockOnHandEntity.class)
public abstract class StockOnHandEntity_ {

	public static volatile SingularAttribute<StockOnHandEntity, PhysicalProductEntity> product;
	public static volatile SingularAttribute<StockOnHandEntity, Long> stockOnHandId;
	public static volatile SingularAttribute<StockOnHandEntity, Long> stockOnHand;

	public static final String PRODUCT = "product";
	public static final String STOCK_ON_HAND_ID = "stockOnHandId";
	public static final String STOCK_ON_HAND = "stockOnHand";

}

