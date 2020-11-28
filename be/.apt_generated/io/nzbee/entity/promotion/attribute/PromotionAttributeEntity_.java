package io.nzbee.entity.promotion.attribute;

import io.nzbee.entity.promotion.PromotionEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionAttributeEntity.class)
public abstract class PromotionAttributeEntity_ {

	public static volatile SingularAttribute<PromotionAttributeEntity, Long> Id;
	public static volatile SingularAttribute<PromotionAttributeEntity, String> promotionDesc;
	public static volatile SingularAttribute<PromotionAttributeEntity, String> locale;
	public static volatile SingularAttribute<PromotionAttributeEntity, PromotionEntity> promotion;

	public static final String ID = "Id";
	public static final String PROMOTION_DESC = "promotionDesc";
	public static final String LOCALE = "locale";
	public static final String PROMOTION = "promotion";

}

