package io.nzbee.entity.inventory.type;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InventoryType.class)
public abstract class InventoryType_ {

	public static volatile SingularAttribute<InventoryType, String> inventoryTypeCode;
	public static volatile SingularAttribute<InventoryType, Long> inventoryTypeId;

	public static final String INVENTORY_TYPE_CODE = "inventoryTypeCode";
	public static final String INVENTORY_TYPE_ID = "inventoryTypeId";

}

