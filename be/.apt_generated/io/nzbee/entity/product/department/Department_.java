package io.nzbee.entity.product.department;

import io.nzbee.entity.product.department.attribute.DepartmentAttribute;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Department.class)
public abstract class Department_ {

	public static volatile SingularAttribute<Department, String> departmentClass;
	public static volatile SingularAttribute<Department, Long> departmentId;
	public static volatile SingularAttribute<Department, String> departmentCode;
	public static volatile ListAttribute<Department, DepartmentAttribute> attributes;

	public static final String DEPARTMENT_CLASS = "departmentClass";
	public static final String DEPARTMENT_ID = "departmentId";
	public static final String DEPARTMENT_CODE = "departmentCode";
	public static final String ATTRIBUTES = "attributes";

}

