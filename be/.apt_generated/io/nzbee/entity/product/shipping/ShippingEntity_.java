package io.nzbee.entity.product.shipping;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingEntity.class)
public abstract class ShippingEntity_ extends io.nzbee.entity.product.ProductEntity_ {

	public static volatile SingularAttribute<ShippingEntity, String> cityName;
	public static volatile SingularAttribute<ShippingEntity, String> countryCode;
	public static volatile SingularAttribute<ShippingEntity, String> postCode;
	public static volatile SingularAttribute<ShippingEntity, String> countryName;

	public static final String CITY_NAME = "cityName";
	public static final String COUNTRY_CODE = "countryCode";
	public static final String POST_CODE = "postCode";
	public static final String COUNTRY_NAME = "countryName";

}

