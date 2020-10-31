package io.nzbee.entity.category.brand;

import io.nzbee.entity.brand.BrandEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryBrandEntity.class)
public abstract class CategoryBrandEntity_ extends io.nzbee.entity.category.CategoryEntity_ {

	public static volatile SetAttribute<CategoryBrandEntity, BrandEntity> brands;

	public static final String BRANDS = "brands";

}

