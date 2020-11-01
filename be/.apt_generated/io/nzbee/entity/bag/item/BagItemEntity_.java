package io.nzbee.entity.bag.item;

import io.nzbee.entity.bag.BagEntity;
import io.nzbee.entity.bag.status.BagItemStatus;
import io.nzbee.entity.product.ProductEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BagItemEntity.class)
public abstract class BagItemEntity_ {

	public static volatile SingularAttribute<BagItemEntity, ProductEntity> product;
	public static volatile SingularAttribute<BagItemEntity, Integer> quantity;
	public static volatile SingularAttribute<BagItemEntity, BagEntity> bag;
	public static volatile SingularAttribute<BagItemEntity, Long> bagItemId;
	public static volatile SingularAttribute<BagItemEntity, BagItemStatus> bagItemStatus;

	public static final String PRODUCT = "product";
	public static final String QUANTITY = "quantity";
	public static final String BAG = "bag";
	public static final String BAG_ITEM_ID = "bagItemId";
	public static final String BAG_ITEM_STATUS = "bagItemStatus";

}

