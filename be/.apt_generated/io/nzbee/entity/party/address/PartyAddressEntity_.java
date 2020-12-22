package io.nzbee.entity.party.address;

import io.nzbee.entity.party.Party;
import io.nzbee.entity.party.address.type.AddressTypeEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PartyAddressEntity.class)
public abstract class PartyAddressEntity_ {

	public static volatile SingularAttribute<PartyAddressEntity, String> addressCountry;
	public static volatile SingularAttribute<PartyAddressEntity, String> addressLine1;
	public static volatile SingularAttribute<PartyAddressEntity, String> addressPostCode;
	public static volatile SingularAttribute<PartyAddressEntity, String> addressLine2;
	public static volatile SingularAttribute<PartyAddressEntity, String> addressLine3;
	public static volatile SingularAttribute<PartyAddressEntity, AddressTypeEntity> type;
	public static volatile SingularAttribute<PartyAddressEntity, Party> party;
	public static volatile SingularAttribute<PartyAddressEntity, Long> addressId;

	public static final String ADDRESS_COUNTRY = "addressCountry";
	public static final String ADDRESS_LINE1 = "addressLine1";
	public static final String ADDRESS_POST_CODE = "addressPostCode";
	public static final String ADDRESS_LINE2 = "addressLine2";
	public static final String ADDRESS_LINE3 = "addressLine3";
	public static final String TYPE = "type";
	public static final String PARTY = "party";
	public static final String ADDRESS_ID = "addressId";

}

