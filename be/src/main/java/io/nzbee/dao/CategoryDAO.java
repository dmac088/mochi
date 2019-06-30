package io.nzbee.dao;

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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import io.nzbee.entity.Brand;
import io.nzbee.entity.Brand_;
import io.nzbee.entity.Category;
import io.nzbee.entity.CategoryAttribute;
import io.nzbee.entity.CategoryAttribute_;
import io.nzbee.entity.CategoryType;
import io.nzbee.entity.CategoryType_;
import io.nzbee.entity.Category_;
import io.nzbee.entity.Hierarchy;
import io.nzbee.entity.Hierarchy_;
import io.nzbee.entity.Product;
import io.nzbee.entity.Product_;
import io.nzbee.variables.CategoryVars;

@Component
public class CategoryDAO implements Dao<Category> {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public Optional<Category> get(long id) {
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
	public List<Category> getAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), CategoryVars.CATEGORY_TYPE_CODE_PRODUCT));
		
		TypedQuery<Category> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
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

	@Override
	public Page<Category> findAll() {
		return null;
	}
	
	
	public List<Category> getByBrands(String hieararchyCode, String categoryTypeCode, List<Long> brandIds, Long level, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		Join<Category, Product> product = root.join(Category_.products);
		Join<Product, Brand> brand = product.join(Product_.brand);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
		if(!brandIds.isEmpty()) {
			conditions.add(brand.get(Brand_.brandId).in(brandIds));
		}
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
	
	public Category getByCategoryDesc(String hieararchyCode, String categoryTypeCode, String categoryDesc, String locale) {
		
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
		
		System.out.println(query.toString());
		
		
		return query.getSingleResult();
	}
	
	
	public Category getByCategoryCode(String hieararchyCode, String categoryTypeCode, String categoryCode, String locale) {
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
		
		return query.getSingleResult();
	}
	
	public List<Category> getByParent(String hieararchyCode, String categoryTypeCode, Long parentCategoryId, String locale) {
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
	
	
	public List<Category> getByParentAndBrands(String hieararchyCode, String categoryTypeCode, String parentCategoryDesc, List<Long> brandIds, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		
		Root<Category> root = cq.from(Category.class);
		
		Join<Category, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<Category, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<Category, Hierarchy> categoryHierarchy = root.join(Category_.hierarchy);
		Join<Category, Product> product = root.join(Category_.products);
		Join<Product, Brand> brand = product.join(Product_.brand);
		Join<Category, Category> parent = root.join(Category_.parent);
		Join<Category, CategoryAttribute> parentCategoryAttribute = parent.join(Category_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
		if(!brandIds.isEmpty()) {
			conditions.add(brand.get(Brand_.brandId).in(brandIds));
		}
		if(!(parentCategoryDesc == null)) {
			conditions.add(cb.equal(parentCategoryAttribute.get(CategoryAttribute_.categoryDesc), parentCategoryDesc));
		}
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<Category> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	

	public List<Category> getByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale) {
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
	
	
	
}
