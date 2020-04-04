package io.nzbee.entity.product.department.attribute;

import io.nzbee.entity.product.department.Department;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DepartmentAttribute.class)
public abstract class DepartmentAttribute_ {

	public static volatile SingularAttribute<DepartmentAttribute, String> lclCd;
	public static volatile SingularAttribute<DepartmentAttribute, Long> Id;
	public static volatile SingularAttribute<DepartmentAttribute, String> departmentDesc;
	public static volatile SingularAttribute<DepartmentAttribute, Department> department;

}

