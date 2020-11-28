package io.nzbee.entity.promotion.attribute;

import io.nzbee.entity.product.ProductEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PromotionAttributeEntity.class)
public abstract class ProductAttributeEntity_ {

	public static volatile SingularAttribute<PromotionAttributeEntity, String> productDesc;
	public static volatile SingularAttribute<PromotionAttributeEntity, ProductEntity> product;
	public static volatile SingularAttribute<PromotionAttributeEntity, String> ProductImage;
	public static volatile SingularAttribute<PromotionAttributeEntity, String> lclCd;
	public static volatile SingularAttribute<PromotionAttributeEntity, Long> Id;
	public static volatile SingularAttribute<PromotionAttributeEntity, String> productLongDesc;

	public static final String PRODUCT_DESC = "productDesc";
	public static final String PRODUCT = "product";
	public static final String PRODUCT_IMAGE = "ProductImage";
	public static final String LCL_CD = "lclCd";
	public static final String ID = "Id";
	public static final String PRODUCT_LONG_DESC = "productLongDesc";

}

