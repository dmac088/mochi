package io.nzbee.entity.promotion;

import io.nzbee.entity.promotion.mechanic.PromotionMechanic;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Promotion.class)
public abstract class Promotion_ {

	public static volatile SingularAttribute<Promotion, String> promotionShortDesc;
	public static volatile SingularAttribute<Promotion, String> promotionLongDesc;
	public static volatile SingularAttribute<Promotion, LocalDateTime> promotionEndDate;
	public static volatile SingularAttribute<Promotion, String> promotionCode;
	public static volatile SingularAttribute<Promotion, LocalDateTime> promotionStartDate;
	public static volatile SingularAttribute<Promotion, PromotionMechanic> promotionMechanic;
	public static volatile SingularAttribute<Promotion, Long> promotionId;

	public static final String PROMOTION_SHORT_DESC = "promotionShortDesc";
	public static final String PROMOTION_LONG_DESC = "promotionLongDesc";
	public static final String PROMOTION_END_DATE = "promotionEndDate";
	public static final String PROMOTION_CODE = "promotionCode";
	public static final String PROMOTION_START_DATE = "promotionStartDate";
	public static final String PROMOTION_MECHANIC = "promotionMechanic";
	public static final String PROMOTION_ID = "promotionId";

}

