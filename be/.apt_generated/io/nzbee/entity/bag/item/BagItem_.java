package io.nzbee.entity.bag.item;

import io.nzbee.entity.bag.Bag;
import io.nzbee.entity.bag.status.BagItemStatus;
import io.nzbee.entity.product.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BagItem.class)
public abstract class BagItem_ {

	public static volatile SingularAttribute<BagItem, Product> product;
	public static volatile SingularAttribute<BagItem, Integer> quantity;
	public static volatile SingularAttribute<BagItem, Bag> bag;
	public static volatile SingularAttribute<BagItem, Long> bagItemId;
	public static volatile SingularAttribute<BagItem, BagItemStatus> bagItemStatus;

	public static final String PRODUCT = "product";
	public static final String QUANTITY = "quantity";
	public static final String BAG = "bag";
	public static final String BAG_ITEM_ID = "bagItemId";
	public static final String BAG_ITEM_STATUS = "bagItemStatus";

}

