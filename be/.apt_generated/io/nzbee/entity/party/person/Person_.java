package io.nzbee.entity.party.person;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ extends io.nzbee.entity.party.Party_ {

	public static volatile SingularAttribute<Person, String> FamilyName;
	public static volatile SingularAttribute<Person, String> givenName;
	public static volatile SingularAttribute<Person, Boolean> enabled;

}

