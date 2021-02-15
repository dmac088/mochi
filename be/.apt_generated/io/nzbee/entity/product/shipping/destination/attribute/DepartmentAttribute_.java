package io.nzbee.entity.product.shipping.destination.attribute;

import io.nzbee.entity.product.department.DepartmentEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ShippingDestinationAttributeEntity.class)
public abstract class DepartmentAttribute_ {

	public static volatile SingularAttribute<ShippingDestinationAttributeEntity, String> lclCd;
	public static volatile SingularAttribute<ShippingDestinationAttributeEntity, Long> Id;
	public static volatile SingularAttribute<ShippingDestinationAttributeEntity, String> departmentDesc;
	public static volatile SingularAttribute<ShippingDestinationAttributeEntity, DepartmentEntity> department;

	public static final String LCL_CD = "lclCd";
	public static final String ID = "Id";
	public static final String DEPARTMENT_DESC = "departmentDesc";
	public static final String DEPARTMENT = "department";

}

