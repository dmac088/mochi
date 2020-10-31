package io.nzbee.entity.product.department.attribute;

import io.nzbee.entity.product.department.DepartmentEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DepartmentAttribute.class)
public abstract class DepartmentAttribute_ {

	public static volatile SingularAttribute<DepartmentAttribute, String> lclCd;
	public static volatile SingularAttribute<DepartmentAttribute, Long> Id;
	public static volatile SingularAttribute<DepartmentAttribute, String> departmentDesc;
	public static volatile SingularAttribute<DepartmentAttribute, DepartmentEntity> department;

	public static final String LCL_CD = "lclCd";
	public static final String ID = "Id";
	public static final String DEPARTMENT_DESC = "departmentDesc";
	public static final String DEPARTMENT = "department";

}

