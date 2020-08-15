package io.nzbee.entity.product.department;

import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.department.attribute.DepartmentAttribute;
import io.nzbee.entity.product.department.attribute.DepartmentAttribute_;

@Component
public class DepartmentDaoImpl  implements IDepartmentDao { 
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public Set<Department> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Department> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<Department> findById(String locale, long id) {
		
		LOGGER.debug("call DepartmentDaoImpl.findById parameters : {}, {}, {}", locale, id);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Department> root = cq.from(Department.class);
		Join<Department, DepartmentAttribute> attribute = root.join(Department_.attributes);
		
		cq.multiselect(	root.get(Department_.departmentId).alias("departmentId"),
						root.get(Department_.departmentCode).alias("departmentCode"),
						attribute.get(DepartmentAttribute_.Id).alias("departmentAttributeId"),
						attribute.get(DepartmentAttribute_.departmentDesc).alias("departmentDesc")
		);
		
		cq.where(cb.and(
				cb.equal(root.get(Department_.departmentId), id),
				cb.equal(attribute.get(DepartmentAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			Department department = this.objectToEntity(tuple, locale);
			return Optional.ofNullable(department);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<Department> findByCode(String locale, String code) {
		
		LOGGER.debug("call DepartmentDaoImpl.findByCode parameters : {}, {}, {}", locale, code);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Department> root = cq.from(Department.class);
		Join<Department, DepartmentAttribute> attribute = root.join(Department_.attributes);
		
		cq.multiselect(	root.get(Department_.departmentId).alias("departmentId"),
						root.get(Department_.departmentCode).alias("departmentCode"),
						attribute.get(DepartmentAttribute_.Id).alias("departmentAttributeId"),
						attribute.get(DepartmentAttribute_.departmentDesc).alias("departmentDesc")
		);
		
		cq.where(cb.and(
				cb.equal(root.get(Department_.departmentCode), code),
				cb.equal(attribute.get(DepartmentAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			Department department = this.objectToEntity(tuple, locale);
			return Optional.ofNullable(department);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	
	@Override
	public Optional<Department> findByDesc(String locale, String desc) {
		LOGGER.debug("call DepartmentDaoImpl.findByDesc parameters : {}, {}, {}", locale, desc);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Department> root = cq.from(Department.class);
		Join<Department, DepartmentAttribute> attribute = root.join(Department_.attributes);
		
		cq.multiselect(	root.get(Department_.departmentId).alias("departmentId"),
						root.get(Department_.departmentCode).alias("departmentCode"),
						attribute.get(DepartmentAttribute_.Id).alias("departmentAttributeId"),
						attribute.get(DepartmentAttribute_.departmentDesc).alias("departmentDesc")
		);
		
		cq.where(cb.and(
				cb.equal(attribute.get(DepartmentAttribute_.departmentDesc), desc),
				cb.equal(attribute.get(DepartmentAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			Department department = this.objectToEntity(tuple, locale);
			return Optional.ofNullable(department);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<Department> findByProductCode(String locale, String productCode) {
		
		LOGGER.debug("call DepartmentDaoImpl.findByProductCode parameters : {}, {}, {}", locale, productCode);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<Product> root = cq.from(Product.class);
		Join<Product, Department> dept = root.join(Product_.department);
		Join<Department, DepartmentAttribute> attribute = dept.join(Department_.attributes);
		
		cq.multiselect(	dept.get(Department_.departmentId).alias("departmentId"),
						dept.get(Department_.departmentCode).alias("departmentCode"),
						attribute.get(DepartmentAttribute_.Id).alias("departmentAttributeId"),
						attribute.get(DepartmentAttribute_.departmentDesc).alias("departmentDesc")
		);
		
		cq.where(cb.and(
				cb.equal(root.get(Product_.productUPC), productCode),
				cb.equal(attribute.get(DepartmentAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			Department department = this.objectToEntity(tuple, locale);
			return Optional.ofNullable(department);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public void save(Department t) {
		em.persist(t);
		em.flush();
	}
	
	@Override
	public void update(Department t, String[] params) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(Department t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department objectToEntity(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department objectToEntity(Tuple t, String locale, String currency) {
		Department departmentEntity = objectToEntity(t, locale);
		departmentEntity.setCurrency(currency);
		return departmentEntity;
	}

	@Override
	public Department objectToEntity(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department objectToEntity(Tuple t, String locale) {
		Department departmentEntity = new Department();
		DepartmentAttribute departmentAttribute = new DepartmentAttribute();
		
		departmentAttribute.setId(Long.parseLong(t.get("departmentAttributeId").toString()));
		departmentAttribute.setDepartment(departmentEntity);
		departmentAttribute.setDesc(t.get("departmentDesc").toString());
		departmentAttribute.setLclCd(locale);
		
		departmentEntity.setAttribute(departmentAttribute);
		departmentEntity.getAttributes().add(departmentAttribute);
		departmentEntity.setId(Long.parseLong(t.get("departmentId").toString()));
		departmentEntity.setDepartmentCode(t.get("departmentCode").toString());
		departmentEntity.setLocale(locale);
		
		return departmentEntity;
	}

}