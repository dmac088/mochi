package io.nzbee.entity.category;

import io.nzbee.entity.category.attribute.CategoryAttributeEntity;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.promotion.product.PromotionProductEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryEntity.class)
public abstract class CategoryEntity_ {

	public static volatile SingularAttribute<CategoryEntity, String> categoryParentCode;
	public static volatile SingularAttribute<CategoryEntity, CategoryType> categoryType;
	public static volatile SingularAttribute<CategoryEntity, Long> categoryParentId;
	public static volatile SingularAttribute<CategoryEntity, CategoryEntity> parent;
	public static volatile SetAttribute<CategoryEntity, PromotionProductEntity> promotions;
	public static volatile SingularAttribute<CategoryEntity, Long> categoryLevel;
	public static volatile SetAttribute<CategoryEntity, CategoryAttributeEntity> attributes;
	public static volatile SingularAttribute<CategoryEntity, String> categoryCode;
	public static volatile SingularAttribute<CategoryEntity, Long> categoryId;

	public static final String CATEGORY_PARENT_CODE = "categoryParentCode";
	public static final String CATEGORY_TYPE = "categoryType";
	public static final String CATEGORY_PARENT_ID = "categoryParentId";
	public static final String PARENT = "parent";
	public static final String PROMOTIONS = "promotions";
	public static final String CATEGORY_LEVEL = "categoryLevel";
	public static final String ATTRIBUTES = "attributes";
	public static final String CATEGORY_CODE = "categoryCode";
	public static final String CATEGORY_ID = "categoryId";

}

