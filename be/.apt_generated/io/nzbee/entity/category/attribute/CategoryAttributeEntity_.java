package io.nzbee.entity.category.attribute;

import io.nzbee.entity.category.CategoryEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryAttributeEntity.class)
public abstract class CategoryAttributeEntity_ {

	public static volatile SingularAttribute<CategoryAttributeEntity, String> lclCd;
	public static volatile SingularAttribute<CategoryAttributeEntity, Long> categoryAttributeId;
	public static volatile SingularAttribute<CategoryAttributeEntity, CategoryEntity> category;
	public static volatile SingularAttribute<CategoryAttributeEntity, String> categoryDesc;

	public static final String LCL_CD = "lclCd";
	public static final String CATEGORY_ATTRIBUTE_ID = "categoryAttributeId";
	public static final String CATEGORY = "category";
	public static final String CATEGORY_DESC = "categoryDesc";

}

