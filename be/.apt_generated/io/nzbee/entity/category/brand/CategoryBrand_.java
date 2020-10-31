package io.nzbee.entity.category.brand;

import io.nzbee.entity.brand.BrandEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryBrand.class)
public abstract class CategoryBrand_ extends io.nzbee.entity.category.Category_ {

	public static volatile SetAttribute<CategoryBrand, BrandEntity> brands;

	public static final String BRANDS = "brands";

}

