package io.nzbee.entity.party.person;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PersonEntity.class)
public abstract class PersonEntity_ extends io.nzbee.entity.party.Party_ {

	public static volatile SingularAttribute<PersonEntity, String> givenName;
	public static volatile SingularAttribute<PersonEntity, String> familyName;
	public static volatile SingularAttribute<PersonEntity, Boolean> enabled;

	public static final String GIVEN_NAME = "givenName";
	public static final String FAMILY_NAME = "familyName";
	public static final String ENABLED = "enabled";

}

