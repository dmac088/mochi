package io.nzbee.entity.promotion;

import io.nzbee.entity.category.CategoryEntity;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.promotion.attribute.PromotionAttributeEntity;
import io.nzbee.entity.promotion.mechanic.PromotionMechanic;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionEntity.class)
public abstract class PromotionEntity_ {

	public static volatile SingularAttribute<PromotionEntity, LocalDateTime> promotionEndDate;
	public static volatile SingularAttribute<PromotionEntity, Boolean> promotionActive;
	public static volatile SingularAttribute<PromotionEntity, String> promotionCode;
	public static volatile SetAttribute<PromotionEntity, PromotionAttributeEntity> attributes;
	public static volatile SetAttribute<PromotionEntity, CategoryEntity> categories;
	public static volatile SingularAttribute<PromotionEntity, LocalDateTime> promotionStartDate;
	public static volatile SingularAttribute<PromotionEntity, PromotionMechanic> promotionMechanic;
	public static volatile SingularAttribute<PromotionEntity, Long> promotionId;
	public static volatile SetAttribute<PromotionEntity, ProductEntity> products;

	public static final String PROMOTION_END_DATE = "promotionEndDate";
	public static final String PROMOTION_ACTIVE = "promotionActive";
	public static final String PROMOTION_CODE = "promotionCode";
	public static final String ATTRIBUTES = "attributes";
	public static final String CATEGORIES = "categories";
	public static final String PROMOTION_START_DATE = "promotionStartDate";
	public static final String PROMOTION_MECHANIC = "promotionMechanic";
	public static final String PROMOTION_ID = "promotionId";
	public static final String PRODUCTS = "products";

}

