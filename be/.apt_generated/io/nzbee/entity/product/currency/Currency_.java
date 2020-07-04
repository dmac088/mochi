package io.nzbee.entity.product.currency;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Currency.class)
public abstract class Currency_ {

	public static volatile SingularAttribute<Currency, String> code;
	public static volatile SingularAttribute<Currency, Long> currencyId;

	public static final String CODE = "code";
	public static final String CURRENCY_ID = "currencyId";

}

