package io.nzbee.entity.promotion;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionBNGNF.class)
public abstract class PromotionBNGNF_ extends io.nzbee.entity.promotion.PromotionEntity_ {

	public static volatile SingularAttribute<PromotionBNGNF, Integer> buyQty;
	public static volatile SingularAttribute<PromotionBNGNF, Double> freeQty;

	public static final String BUY_QTY = "buyQty";
	public static final String FREE_QTY = "freeQty";

}

