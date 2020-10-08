package io.nzbee.entity.inventory.location;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InventoryLocation.class)
public abstract class InventoryLocation_ {

	public static volatile SingularAttribute<InventoryLocation, Long> locationId;
	public static volatile SingularAttribute<InventoryLocation, String> locationDesc;
	public static volatile SingularAttribute<InventoryLocation, String> locationCode;
	public static volatile SingularAttribute<InventoryLocation, Boolean> locationIsActive;

	public static final String LOCATION_ID = "locationId";
	public static final String LOCATION_DESC = "locationDesc";
	public static final String LOCATION_CODE = "locationCode";
	public static final String LOCATION_IS_ACTIVE = "locationIsActive";

}

