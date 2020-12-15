package io.nzbee.entity.product.shipping;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingProductEntity.class)
public abstract class ShippingProductEntity_ extends io.nzbee.entity.product.ProductEntity_ {

	public static volatile SingularAttribute<ShippingProductEntity, String> cityName;
	public static volatile SingularAttribute<ShippingProductEntity, String> countryCode;
	public static volatile SingularAttribute<ShippingProductEntity, String> postCode;
	public static volatile SingularAttribute<ShippingProductEntity, String> countryName;

	public static final String CITY_NAME = "cityName";
	public static final String COUNTRY_CODE = "countryCode";
	public static final String POST_CODE = "postCode";
	public static final String COUNTRY_NAME = "countryName";

}

