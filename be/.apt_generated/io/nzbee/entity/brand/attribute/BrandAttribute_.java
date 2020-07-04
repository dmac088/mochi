package io.nzbee.entity.brand.attribute;

import io.nzbee.entity.brand.Brand;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BrandAttribute.class)
public abstract class BrandAttribute_ {

	public static volatile SingularAttribute<BrandAttribute, String> brandDesc;
	public static volatile SingularAttribute<BrandAttribute, String> lclCd;
	public static volatile SingularAttribute<BrandAttribute, Long> brandAttributeId;
	public static volatile SingularAttribute<BrandAttribute, Brand> brand;

	public static final String BRAND_DESC = "brandDesc";
	public static final String LCL_CD = "lclCd";
	public static final String BRAND_ATTRIBUTE_ID = "brandAttributeId";
	public static final String BRAND = "brand";

}

