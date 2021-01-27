package io.nzbee.entity.product.shipping;

import io.nzbee.entity.postage.customs.CustomsFormEntity;
import io.nzbee.entity.postage.destination.PostageDestinationEntity;
import io.nzbee.entity.postage.insurance.PostageInsuranceEntity;
import io.nzbee.entity.postage.size.PostageSizeEntity;
import io.nzbee.entity.postage.type.PostageTypeEntity;
import io.nzbee.entity.postage.zone.PostageZoneEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingProductEntity.class)
public abstract class ShippingProductEntity_ extends io.nzbee.entity.product.ProductEntity_ {

	public static volatile SingularAttribute<ShippingProductEntity, PostageSizeEntity> postageSize;
	public static volatile SingularAttribute<ShippingProductEntity, CustomsFormEntity> customsForm;
	public static volatile SingularAttribute<ShippingProductEntity, PostageInsuranceEntity> postageInsurance;
	public static volatile SingularAttribute<ShippingProductEntity, PostageTypeEntity> postageType;
	public static volatile SingularAttribute<ShippingProductEntity, PostageDestinationEntity> postageDestination;
	public static volatile SingularAttribute<ShippingProductEntity, PostageZoneEntity> postageZone;

	public static final String POSTAGE_SIZE = "postageSize";
	public static final String CUSTOMS_FORM = "customsForm";
	public static final String POSTAGE_INSURANCE = "postageInsurance";
	public static final String POSTAGE_TYPE = "postageType";
	public static final String POSTAGE_DESTINATION = "postageDestination";
	public static final String POSTAGE_ZONE = "postageZone";

}

