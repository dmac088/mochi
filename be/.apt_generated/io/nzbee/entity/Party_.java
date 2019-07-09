package io.nzbee.entity;

import io.nzbee.security.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Party.class)
public abstract class Party_ {

	public static volatile SingularAttribute<Party, Long> partyId;
	public static volatile SingularAttribute<Party, PartyType> partyType;
	public static volatile ListAttribute<Party, Role> partyRoles;
	public static volatile SingularAttribute<Party, User> partyUser;

}

