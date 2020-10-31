package io.nzbee.entity.inventory;

import io.nzbee.entity.inventory.location.InventoryLocation;
import io.nzbee.entity.inventory.type.InventoryType;
import io.nzbee.entity.party.organization.Organization;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.currency.Currency;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InventoryTransaction.class)
public abstract class InventoryTransaction_ {

	public static volatile SingularAttribute<InventoryTransaction, ProductEntity> product;
	public static volatile SingularAttribute<InventoryTransaction, InventoryType> inventoryType;
	public static volatile SingularAttribute<InventoryTransaction, Long> quantity;
	public static volatile SingularAttribute<InventoryTransaction, Double> price;
	public static volatile SingularAttribute<InventoryTransaction, Organization> supplier;
	public static volatile SingularAttribute<InventoryTransaction, Long> inventroyTransactionId;
	public static volatile SingularAttribute<InventoryTransaction, InventoryLocation> inventoryLocation;
	public static volatile SingularAttribute<InventoryTransaction, LocalDateTime> inventoryTransactionDate;
	public static volatile SingularAttribute<InventoryTransaction, Currency> currency;

	public static final String PRODUCT = "product";
	public static final String INVENTORY_TYPE = "inventoryType";
	public static final String QUANTITY = "quantity";
	public static final String PRICE = "price";
	public static final String SUPPLIER = "supplier";
	public static final String INVENTROY_TRANSACTION_ID = "inventroyTransactionId";
	public static final String INVENTORY_LOCATION = "inventoryLocation";
	public static final String INVENTORY_TRANSACTION_DATE = "inventoryTransactionDate";
	public static final String CURRENCY = "currency";

}

