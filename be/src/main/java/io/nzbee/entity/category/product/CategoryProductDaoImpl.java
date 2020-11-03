package io.nzbee.entity.category.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import io.nzbee.entity.category.CategoryEntity_;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity;
import io.nzbee.entity.category.attribute.CategoryAttributeEntity_;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.category.type.CategoryType_;
import io.nzbee.entity.product.ProductEntity;
import io.nzbee.entity.product.ProductEntity_;

@Service
public class CategoryProductDaoImpl implements ICategoryProductDao {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;
	
	@Override
	public List<CategoryProductDTO> findAllByProductCode(String locale, String productCode) {
		LOGGER.debug("call CategoryProductDaoImpl.findAllByProductCode parameters : {}, {}, {}", locale, productCode);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<CategoryProductEntity> root = cq.from(CategoryProductEntity.class);
		
		Join<CategoryProductEntity, CategoryAttributeEntity> attribute = root.join(CategoryEntity_.attributes);
		Join<CategoryProductEntity, ProductEntity> products = root.join(CategoryProductEntity_.products);
		Join<CategoryProductEntity, CategoryType> type = root.join(CategoryEntity_.categoryType);
		
		cq.multiselect(	root.get(CategoryProductEntity_.categoryId).alias("categoryId"),
						root.get(CategoryProductEntity_.categoryCode).alias("categoryCode"),
						attribute.get(CategoryAttributeEntity_.categoryAttributeId).alias("categoryAttributeId"),
						attribute.get(CategoryAttributeEntity_.categoryDesc).alias("categoryDesc"),
						type.get(CategoryType_.categoryTypeCode).alias("categoryTypeCode"),
						type.get(CategoryType_.categoryTypeDesc).alias("categoryTypeDesc"),
						root.get(CategoryEntity_.categoryLevel).alias("categoryLevel")
		);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
	
		if(!(productCode == null)) {
			
			cq.where(cb.and(
					cb.equal(products.get(ProductEntity_.productUPC), productCode),
					cb.equal(attribute.get(CategoryAttributeEntity_.lclCd), locale)
					)
			);
			
			conditions.add(cb.equal(products.get(ProductEntity_.productUPC), productCode));
		}
		
		TypedQuery<Tuple> query = em.createQuery(cq);
		List<Tuple> tuples = query.getResultList();
		
		return tuples.stream().map(t -> this.objectToDTO(t, locale)).collect(Collectors.toList());
	}
	
	@Override
	public Optional<CategoryProductDTO> findPrimaryByProductCode(String locale, String productCode) {
		LOGGER.debug("call CategoryProductDaoImpl.findPrimaryByProductCode parameters : {}, {}, {}", locale, productCode);
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> cq = cb.createQuery(Tuple.class);
		
		Root<ProductEntity> root = cq.from(ProductEntity.class);
		Join<ProductEntity, CategoryProductEntity> primaryCategory = root.join(ProductEntity_.primaryCategoryIndex);
		Join<CategoryProductEntity, CategoryAttributeEntity> attribute = primaryCategory.join(CategoryEntity_.attributes);
		Join<CategoryProductEntity, CategoryType> type = primaryCategory.join(CategoryEntity_.categoryType);
		
		cq.multiselect(	primaryCategory.get(CategoryProductEntity_.categoryId).alias("categoryId"),
						primaryCategory.get(CategoryProductEntity_.categoryCode).alias("categoryCode"),
						attribute.get(CategoryAttributeEntity_.categoryAttributeId).alias("categoryAttributeId"),
						attribute.get(CategoryAttributeEntity_.categoryDesc).alias("categoryDesc"),
						type.get(CategoryType_.categoryTypeCode).alias("categoryTypeCode"),
						type.get(CategoryType_.categoryTypeDesc).alias("categoryTypeDesc"),
						primaryCategory.get(CategoryEntity_.categoryLevel).alias("categoryLevel")
		);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
	
		if(!(productCode == null)) {
			
			cq.where(cb.and(
					cb.equal(root.get(ProductEntity_.productUPC), productCode),
					cb.equal(attribute.get(CategoryAttributeEntity_.lclCd), locale)
					)
			);
			
			conditions.add(cb.equal(root.get(ProductEntity_.productUPC), productCode));
		}
		
		TypedQuery<Tuple> query = em.createQuery(cq);
	
		try {
			Tuple tuple = query.getSingleResult();
			
			CategoryProductDTO category = this.objectToDTO(tuple, locale);
			return Optional.ofNullable(category);
		} 
		catch(NoResultException nre) {
			return Optional.empty();
		}
	}
	
	@Override
	public Optional<CategoryProductDTO> findById(String locale, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProductDTO> findByCode(String locale, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProductDTO> findByDesc(String locale, String desc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CategoryProductDTO> findAll(String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CategoryProductDTO> findAll(String locale, Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(CategoryProductEntity t) {
		em.persist(t);
	}

	@Override
	public void update(CategoryProductEntity t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryProductEntity t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryProductDTO objectToDTO(Tuple t, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryProductDTO objectToDTO(Object[] o, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryProductDTO objectToDTO(Tuple t, String locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryProductDTO objectToDTO(Object[] o, String locale, String currency) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProductEntity> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CategoryProductEntity> findByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CategoryProductEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<CategoryProductEntity> findAll(Set<String> codes) {
		// TODO Auto-generated method stub
		return null;
	}

}
