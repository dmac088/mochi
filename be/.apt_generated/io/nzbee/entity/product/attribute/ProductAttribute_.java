package io.nzbee.entity.product.attribute;

import io.nzbee.entity.product.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductAttribute.class)
public abstract class ProductAttribute_ {

	public static volatile SingularAttribute<ProductAttribute, String> productDesc;
	public static volatile SingularAttribute<ProductAttribute, Product> product;
	public static volatile SingularAttribute<ProductAttribute, String> ProductImage;
	public static volatile SingularAttribute<ProductAttribute, String> lclCd;
	public static volatile SingularAttribute<ProductAttribute, Long> Id;

	public static final String PRODUCT_DESC = "productDesc";
	public static final String PRODUCT = "product";
	public static final String PRODUCT_IMAGE = "ProductImage";
	public static final String LCL_CD = "lclCd";
	public static final String ID = "Id";

}

