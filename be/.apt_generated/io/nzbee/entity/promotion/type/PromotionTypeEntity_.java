package io.nzbee.entity.promotion.type;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionTypeEntity.class)
public abstract class PromotionTypeEntity_ {

	public static volatile SingularAttribute<PromotionTypeEntity, Long> promotionTypeId;
	public static volatile SingularAttribute<PromotionTypeEntity, String> promotionTypeDesc;
	public static volatile SingularAttribute<PromotionTypeEntity, String> promotionTypeCode;
	public static volatile SingularAttribute<PromotionTypeEntity, String> promotionClass;

	public static final String PROMOTION_TYPE_ID = "promotionTypeId";
	public static final String PROMOTION_TYPE_DESC = "promotionTypeDesc";
	public static final String PROMOTION_TYPE_CODE = "promotionTypeCode";
	public static final String PROMOTION_CLASS = "promotionClass";

}

