package io.nzbee.entity.promotion.regular;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "promotion_regular", schema = "mochi")
@DiscriminatorValue("1")
public class PromotionRegularEntity {

}
