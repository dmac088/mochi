package io.nzbee.entity.promotion.type;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionType.class)
public abstract class PromotionType_ {

	public static volatile SingularAttribute<PromotionType, Long> promotionTypeId;
	public static volatile SingularAttribute<PromotionType, String> promotionTypeDesc;
	public static volatile SingularAttribute<PromotionType, String> promotionTypeCode;

	public static final String PROMOTION_TYPE_ID = "promotionTypeId";
	public static final String PROMOTION_TYPE_DESC = "promotionTypeDesc";
	public static final String PROMOTION_TYPE_CODE = "promotionTypeCode";

}

