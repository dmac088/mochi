package io.nzbee.entity.person;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PartyPerson.class)
public abstract class PartyPerson_ extends io.nzbee.entity.party.Party_ {

	public static volatile SingularAttribute<PartyPerson, String> FamilyName;
	public static volatile SingularAttribute<PartyPerson, String> GivenName;

}

