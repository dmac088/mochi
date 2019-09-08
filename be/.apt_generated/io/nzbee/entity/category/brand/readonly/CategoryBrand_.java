package io.nzbee.entity.category.brand.readonly;

import io.nzbee.entity.brand.Brand;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CategoryBrand.class)
public abstract class CategoryBrand_ extends io.nzbee.entity.category.Category_ {

	public static volatile ListAttribute<CategoryBrand, Brand> brands;
	public static volatile SingularAttribute<CategoryBrand, Long> brandCount;

}

