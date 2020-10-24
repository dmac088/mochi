package io.nzbee.entity.promotion;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import io.nzbee.entity.promotion.mechanic.PromotionMechanic;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionMechanic.class)
public abstract class PromotionMechanic_ {

	public static volatile SingularAttribute<PromotionMechanic, String> promotionMechanicDesc;
	public static volatile SingularAttribute<PromotionMechanic, String> promotionMechanicCode;
	public static volatile SingularAttribute<PromotionMechanic, Long> promotionMechanicId;

	public static final String PROMOTION_MECHANIC_DESC = "promotionMechanicDesc";
	public static final String PROMOTION_MECHANIC_CODE = "promotionMechanicCode";
	public static final String PROMOTION_MECHANIC_ID = "promotionMechanicId";

}

