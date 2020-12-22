package io.nzbee.entity.party.address.type;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AddressTypeEntity.class)
public abstract class AddressTypeEntity_ {

	public static volatile SingularAttribute<AddressTypeEntity, String> addressTypeCode;
	public static volatile SingularAttribute<AddressTypeEntity, Long> addressTypeId;
	public static volatile SingularAttribute<AddressTypeEntity, String> addressTypeDesc;

	public static final String ADDRESS_TYPE_CODE = "addressTypeCode";
	public static final String ADDRESS_TYPE_ID = "addressTypeId";
	public static final String ADDRESS_TYPE_DESC = "addressTypeDesc";

}

