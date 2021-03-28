package io.nzbee.entity.promotion.regular;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.nzbee.entity.promotion.PromotionEntity;

@Entity
@Table(name = "promotion_regular", schema = "mochi")
@DiscriminatorValue("1")
public class PromotionRegularEntity extends PromotionEntity {

	private static final long serialVersionUID = -3935115006992696518L;

}
