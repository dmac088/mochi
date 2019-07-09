package io.nzbee.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Brand.class)
public abstract class Brand_ {

	public static volatile SingularAttribute<Brand, Long> brandId;
	public static volatile ListAttribute<Brand, Category> categories;
	public static volatile ListAttribute<Brand, BrandAttribute> brandAttributes;
	public static volatile SingularAttribute<Brand, String> brandCode;
	public static volatile ListAttribute<Brand, Product> products;

}

