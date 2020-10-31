package io.nzbee.entity.product.department;

import io.nzbee.entity.product.department.attribute.DepartmentAttribute;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DepartmentEntity.class)
public abstract class DepartmentEntity_ {

	public static volatile SingularAttribute<DepartmentEntity, String> departmentClass;
	public static volatile SingularAttribute<DepartmentEntity, Long> departmentId;
	public static volatile SingularAttribute<DepartmentEntity, String> departmentCode;
	public static volatile ListAttribute<DepartmentEntity, DepartmentAttribute> attributes;

	public static final String DEPARTMENT_CLASS = "departmentClass";
	public static final String DEPARTMENT_ID = "departmentId";
	public static final String DEPARTMENT_CODE = "departmentCode";
	public static final String ATTRIBUTES = "attributes";

}

