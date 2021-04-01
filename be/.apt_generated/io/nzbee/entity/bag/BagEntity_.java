package io.nzbee.entity.bag;

import io.nzbee.entity.bag.item.BagItemEntity;
import io.nzbee.entity.party.Party;
import io.nzbee.entity.promotion.order.PromotionOrderEntity;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BagEntity.class)
public abstract class BagEntity_ {

	public static volatile SingularAttribute<BagEntity, Long> bagId;
	public static volatile SetAttribute<BagEntity, BagItemEntity> bagItems;
	public static volatile SingularAttribute<BagEntity, LocalDateTime> bagCreatedDateTime;
	public static volatile SingularAttribute<BagEntity, LocalDateTime> bagUpdatedDateTime;
	public static volatile SingularAttribute<BagEntity, Party> party;
	public static volatile SingularAttribute<BagEntity, PromotionOrderEntity> promotion;

	public static final String BAG_ID = "bagId";
	public static final String BAG_ITEMS = "bagItems";
	public static final String BAG_CREATED_DATE_TIME = "bagCreatedDateTime";
	public static final String BAG_UPDATED_DATE_TIME = "bagUpdatedDateTime";
	public static final String PARTY = "party";
	public static final String PROMOTION = "promotion";

}

