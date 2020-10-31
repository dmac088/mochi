package io.nzbee.entity.brand.attribute;

import io.nzbee.entity.brand.BrandEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BrandAttributeEntity.class)
public abstract class BrandAttributeEntity_ {

	public static volatile SingularAttribute<BrandAttributeEntity, String> brandDesc;
	public static volatile SingularAttribute<BrandAttributeEntity, String> lclCd;
	public static volatile SingularAttribute<BrandAttributeEntity, Long> brandAttributeId;
	public static volatile SingularAttribute<BrandAttributeEntity, BrandEntity> brand;

	public static final String BRAND_DESC = "brandDesc";
	public static final String LCL_CD = "lclCd";
	public static final String BRAND_ATTRIBUTE_ID = "brandAttributeId";
	public static final String BRAND = "brand";

}

