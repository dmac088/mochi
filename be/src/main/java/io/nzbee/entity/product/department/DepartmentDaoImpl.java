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
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.ProductEntity_;
import io.nzbee.entity.product.department.attribute.DepartmentAttribute;
import io.nzbee.entity.product.department.attribute.DepartmentAttribute_;

@Component
public class DepartmentDaoImpl  implements IDepartmentDao { 
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Autowired
	private IDepartmentRepository departmentRepository;
	
	@Override
	public Set<DepartmentDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<DepartmentDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<DepartmentEntity> findById(long id) {
		return departmentRepository.findById(id);
	}
	
	@Override
	public Optional<DepartmentDTO> findById(String locale, Long id) {
		
		LOGGER.debug("call DepartmentDaoImpl.findById parameters : {}, {}", locale, id);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<DepartmentDTO> cq = cb.createQuery(DepartmentDTO.class);
		
		Root<DepartmentEntity> root = cq.from(DepartmentEntity.class);
		Join<DepartmentEntity, DepartmentAttribute> attribute = root.join(DepartmentEntity_.attributes);
		
		cq.select(cb.construct(		DepartmentDTO.class, 
							   		root.get(DepartmentEntity_.departmentId),
							   		root.get(DepartmentEntity_.departmentCode),
							   		attribute.get(DepartmentAttribute_.departmentDesc),
							   		attribute.get(DepartmentAttribute_.lclCd)
		));
		
		cq.where(cb.and(
				 cb.equal(root.get(DepartmentEntity_.departmentId), id),
				 cb.equal(attribute.get(DepartmentAttribute_.lclCd), locale))
		);

		
		try {
			DepartmentDTO department = em.createQuery(cq).getSingleResult();
			return Optional.ofNullable(department);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<DepartmentDTO> findByCode(String locale, String code) {
		
		LOGGER.debug("call DepartmentDaoImpl.findByCode parameters : {}, {}, {}", locale, code);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<DepartmentEntity> root = cq.from(DepartmentEntity.class);
		Join<DepartmentEntity, DepartmentAttribute> attribute = root.join(DepartmentEntity_.attributes);
		
		cq.multiselect(	root.get(DepartmentEntity_.departmentId).alias("departmentId"),
						root.get(DepartmentEntity_.departmentCode).alias("departmentCode"),
						attribute.get(DepartmentAttribute_.Id).alias("departmentAttributeId"),
						attribute.get(DepartmentAttribute_.departmentDesc).alias("departmentDesc")
		);
		
		cq.where(cb.and(
				cb.equal(root.get(DepartmentEntity_.departmentCode), code),
				cb.equal(attribute.get(DepartmentAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			DepartmentDTO department = this.objectToDTO(tuple, locale);
			return Optional.ofNullable(department);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}

	
	@Override
	public Optional<DepartmentDTO> findByDesc(String locale, String desc) {
		LOGGER.debug("call DepartmentDaoImpl.findByDesc parameters : {}, {}, {}", locale, desc);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<DepartmentEntity> root = cq.from(DepartmentEntity.class);
		Join<DepartmentEntity, DepartmentAttribute> attribute = root.join(DepartmentEntity_.attributes);
		
		cq.multiselect(	root.get(DepartmentEntity_.departmentId).alias("departmentId"),
						root.get(DepartmentEntity_.departmentCode).alias("departmentCode"),
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
			
			DepartmentDTO department = this.objectToDTO(tuple, locale);
			return Optional.ofNullable(department);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<DepartmentDTO> findByProductCode(String locale, String productCode) {
		
		LOGGER.debug("call DepartmentDaoImpl.findByProductCode parameters : {}, {}, {}", locale, productCode);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<ProductEntity> root = cq.from(ProductEntity.class);
		Join<ProductEntity, DepartmentEntity> dept = root.join(ProductEntity_.department);
		Join<DepartmentEntity, DepartmentAttribute> attribute = dept.join(DepartmentEntity_.attributes);
		
		cq.multiselect(	dept.get(DepartmentEntity_.departmentId).alias("departmentId"),
						dept.get(DepartmentEntity_.departmentCode).alias("departmentCode"),
						attribute.get(DepartmentAttribute_.Id).alias("departmentAttributeId"),
						attribute.get(DepartmentAttribute_.departmentDesc).alias("departmentDesc")
		);
		
		cq.where(cb.and(
				cb.equal(root.get(ProductEntity_.productUPC), productCode),
				cb.equal(attribute.get(DepartmentAttribute_.lclCd), locale)
				)
		);
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		
		try {
			Tuple tuple = query.getSingleResult();
			
			DepartmentDTO department = this.objectToDTO(tuple, locale);
			return Optional.ofNullable(department);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public void save(DepartmentEntity t) {
		em.persist(t);
	}
	
	@Override
	public void update(DepartmentEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(DepartmentEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DepartmentDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentDTO objectToDTO(Tuple t, String locale) {
		return null;
		
	}

	@Override
	public DepartmentDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<DepartmentEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<DepartmentEntity> findAll() {
		return departmentRepository.findAll();
	}

	@Override
	public Set<DepartmentEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}
	
}