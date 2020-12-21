package io.nzbee.entity.party.address.type;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AddressType.class)
public abstract class AddressType_ {

	public static volatile SingularAttribute<AddressType, String> addressTypeCode;
	public static volatile SingularAttribute<AddressType, Long> addressTypeId;
	public static volatile SingularAttribute<AddressType, String> addressTypeDesc;

	public static final String ADDRESS_TYPE_CODE = "addressTypeCode";
	public static final String ADDRESS_TYPE_ID = "addressTypeId";
	public static final String ADDRESS_TYPE_DESC = "addressTypeDesc";

}

