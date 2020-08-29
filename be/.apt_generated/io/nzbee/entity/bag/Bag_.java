package io.nzbee.entity.bag;

import io.nzbee.entity.bag.item.BagItem;
import io.nzbee.entity.bag.status.BagStatus;
import io.nzbee.entity.party.Party;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Bag.class)
public abstract class Bag_ {

	public static volatile SingularAttribute<Bag, Long> bagId;
	public static volatile SingularAttribute<Bag, BagStatus> bagStatus;
	public static volatile SetAttribute<Bag, BagItem> bagItems;
	public static volatile SingularAttribute<Bag, LocalDateTime> bagCreatedDateTime;
	public static volatile SingularAttribute<Bag, LocalDateTime> bagUpdatedDateTime;
	public static volatile SingularAttribute<Bag, Party> party;

	public static final String BAG_ID = "bagId";
	public static final String BAG_STATUS = "bagStatus";
	public static final String BAG_ITEMS = "bagItems";
	public static final String BAG_CREATED_DATE_TIME = "bagCreatedDateTime";
	public static final String BAG_UPDATED_DATE_TIME = "bagUpdatedDateTime";
	public static final String PARTY = "party";

}

