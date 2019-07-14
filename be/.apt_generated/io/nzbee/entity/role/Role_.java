package io.nzbee.entity.role;

import io.nzbee.entity.party.Party;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ {

	public static volatile SingularAttribute<Role, Long> roleId;
	public static volatile SingularAttribute<Role, Party> roleParty;
	public static volatile SingularAttribute<Role, Date> RoleStart;
	public static volatile SingularAttribute<Role, RoleType> roleType;

}

