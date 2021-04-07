package io.nzbee.entity.product.physical;

import io.nzbee.entity.stock.StockOnHandEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PhysicalProductEntity.class)
public abstract class PhysicalProductEntity_ extends io.nzbee.entity.product.ProductEntity_ {

	public static volatile SingularAttribute<PhysicalProductEntity, Integer> heightDimension;
	public static volatile SingularAttribute<PhysicalProductEntity, StockOnHandEntity> stockOnHand;
	public static volatile SingularAttribute<PhysicalProductEntity, Integer> lengthDimension;
	public static volatile SingularAttribute<PhysicalProductEntity, Double> weightDimension;
	public static volatile SingularAttribute<PhysicalProductEntity, Integer> widthDimension;

	public static final String HEIGHT_DIMENSION = "heightDimension";
	public static final String STOCK_ON_HAND = "stockOnHand";
	public static final String LENGTH_DIMENSION = "lengthDimension";
	public static final String WEIGHT_DIMENSION = "weightDimension";
	public static final String WIDTH_DIMENSION = "widthDimension";

}

