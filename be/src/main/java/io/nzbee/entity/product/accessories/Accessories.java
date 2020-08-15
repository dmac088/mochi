package io.nzbee.entity.product.accessories;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

import io.nzbee.entity.product.Product;

@Indexed
@Entity
@Table(name = "product_accessories", schema = "mochi")
@PrimaryKeyJoinColumn(name = "prd_id")
@DiscriminatorValue("2")
public class Accessories extends Product  {


	
}
