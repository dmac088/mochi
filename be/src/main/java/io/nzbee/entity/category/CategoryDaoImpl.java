
package io.nzbee.entity.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.Category_;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute_;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.category.type.CategoryType_;
import io.nzbee.entity.product.hierarchy.Hierarchy;
import io.nzbee.entity.product.hierarchy.Hierarchy_;

@Component
public class CategoryDaoImpl implements ICategoryDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public Optional<Category> findById(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(root.get(Category_.categoryId), id));
	
		TypedQuery<Category> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}

	@Override
	public List<Category> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<Category> root = cq.from(Category.class);
				
		TypedQuery<Category> query = em.createQuery(cq
				.select(root)
				.distinct(true)
		);
		
		return query.getResultList();
	}


	@Override
	public void save(Category t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Category t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Category t) {
		// TODO Auto-generated method stub
	}

	
	
	public Optional<Category> findByCategoryDesc(String categoryTypeCode, String categoryDesc, String locale) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		//Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
		if(!(categoryDesc == null)) {
			conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.categoryDesc), categoryDesc));
		}
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<Category> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
		
	public Optional<Category> findByCategoryCode(String categoryTypeCode, String categoryCode, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<Category> root = cq.from(Category.class);
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		
		//Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
		if(!(categoryCode == null)) {
			conditions.add(cb.equal(root.get(Category_.categoryCode), categoryCode));
		}
		
		TypedQuery<Category> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
	
	public List<Category> findByParent(String hieararchyCode, String categoryTypeCode, Long parentCategoryId, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		Join<Category, Category> parent = root.join(Category_.parent);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
		if(!(parentCategoryId == null)) {
			conditions.add(cb.equal(parent.get(Category_.categoryId), parentCategoryId));
		}
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<Category> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	
	
	
	public List<Category> findByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		if(!(level == null)) {
			conditions.add(cb.equal(root.get(Category_.categoryLevel), level));
		}
	
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<Category> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}

	@Override
	public List<Category> getAll() {
		// TODO Auto-generated method stub
		return this.findAll();
	}
	
	
	
}
