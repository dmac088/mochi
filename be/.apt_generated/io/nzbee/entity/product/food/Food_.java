package io.nzbee.entity.product.food;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Food.class)
public abstract class Food_ extends io.nzbee.entity.product.Product_ {

	public static volatile SingularAttribute<Food, Date> expiryDate;
	public static volatile SingularAttribute<Food, String> countryOfOrigin;

}

