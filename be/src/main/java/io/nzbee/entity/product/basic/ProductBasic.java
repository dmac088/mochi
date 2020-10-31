package io.nzbee.entity.product.basic;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

import io.nzbee.entity.product.ProductEntity;

@Indexed
@Entity
@Table(name = "product_basic", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_id")
@DiscriminatorValue("2")
public class ProductBasic extends ProductEntity  {


	
}
