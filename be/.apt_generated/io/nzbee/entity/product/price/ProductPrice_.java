package io.nzbee.entity.product.price;

import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.currency.Currency;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductPrice.class)
public abstract class ProductPrice_ {

	public static volatile SingularAttribute<ProductPrice, Product> product;
	public static volatile SingularAttribute<ProductPrice, Double> priceValue;
	public static volatile SingularAttribute<ProductPrice, Currency> currency;
	public static volatile SingularAttribute<ProductPrice, Long> id;
	public static volatile SingularAttribute<ProductPrice, ProductPriceType> type;

}

