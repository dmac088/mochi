package io.nzbee.security.authority;

import io.nzbee.security.user.role.UserRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Authority.class)
public abstract class Authority_ {

	public static volatile ListAttribute<Authority, UserRole> userRoles;
	public static volatile SingularAttribute<Authority, String> name;
	public static volatile SingularAttribute<Authority, Long> id;

}

