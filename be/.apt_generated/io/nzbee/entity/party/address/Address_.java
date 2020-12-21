package io.nzbee.entity.party.address;

import io.nzbee.entity.party.Party;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Address.class)
public abstract class Address_ {

	public static volatile SingularAttribute<Address, String> addressCountry;
	public static volatile SingularAttribute<Address, Party> addressParty;
	public static volatile SingularAttribute<Address, Party> addressType;
	public static volatile SingularAttribute<Address, String> addressLine1;
	public static volatile SingularAttribute<Address, String> addressPostCode;
	public static volatile SingularAttribute<Address, String> addressLine2;
	public static volatile SingularAttribute<Address, String> addressLine3;
	public static volatile SingularAttribute<Address, Long> addressId;

	public static final String ADDRESS_COUNTRY = "addressCountry";
	public static final String ADDRESS_PARTY = "addressParty";
	public static final String ADDRESS_TYPE = "addressType";
	public static final String ADDRESS_LINE1 = "addressLine1";
	public static final String ADDRESS_POST_CODE = "addressPostCode";
	public static final String ADDRESS_LINE2 = "addressLine2";
	public static final String ADDRESS_LINE3 = "addressLine3";
	public static final String ADDRESS_ID = "addressId";

}

