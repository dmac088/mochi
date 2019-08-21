
package io.nzbee.entity.category.product;

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
import io.nzbee.entity.brand.Brand;
import io.nzbee.entity.brand.Brand_;
import io.nzbee.entity.category.Category;
import io.nzbee.entity.category.Category_;
import io.nzbee.entity.category.attribute.CategoryAttribute;
import io.nzbee.entity.category.attribute.CategoryAttribute_;
import io.nzbee.entity.category.product.CategoryProduct_;
import io.nzbee.entity.category.type.CategoryType;
import io.nzbee.entity.category.type.CategoryType_;
import io.nzbee.entity.product.Product;
import io.nzbee.entity.product.Product_;
import io.nzbee.entity.product.hierarchy.Hierarchy;
import io.nzbee.entity.product.hierarchy.Hierarchy_;
import io.nzbee.entity.product.tag.ProductTag;
import io.nzbee.entity.product.tag.ProductTag_;
import io.nzbee.variables.CategoryVars;

@Component
public class CategoryProductDaoImpl implements ICategoryProductDao {

	@Autowired
	@Qualifier("mochiEntityManagerFactory")
	private EntityManager em;

	@Override
	public Optional<CategoryProduct> findById(long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryProduct> cq = cb.createQuery(CategoryProduct.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(root.get(CategoryProduct_.categoryId), id));
	
		TypedQuery<CategoryProduct> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}

	@Override
	public List<CategoryProduct> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryProduct> cq = cb.createQuery(CategoryProduct.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		
		Join<CategoryProduct, CategoryType> categoryType = root.join(CategoryProduct_.categoryType);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), CategoryVars.CATEGORY_TYPE_CODE_PRODUCT));
		
		TypedQuery<CategoryProduct> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		//List<Category> cl = query.getResultList().stream().map(c -> (Category) c).collect(Collectors.toList());
		
		return query.getResultList();
	}


	@Override
	public void save(CategoryProduct t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CategoryProduct t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CategoryProduct t) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<CategoryProduct> findByBrandIds(String hieararchyCode, String categoryTypeCode, List<Long> brandIds, Long level, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryProduct> cq = cb.createQuery(CategoryProduct.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		
		Join<CategoryProduct, CategoryType> categoryType = root.join(Category_.categoryType);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = root.join(Category_.attributes);
		Join<CategoryProduct, Hierarchy> categoryHierarchy = root.join(CategoryProduct_.hierarchy);
		Join<CategoryProduct, Product> product = root.join(CategoryProduct_.products);
		Join<Product, Brand> brand = product.join(Product_.brand);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
		if(!brandIds.isEmpty()) {
			conditions.add(brand.get(Brand_.brandId).in(brandIds));
		}
		if(!(level == null)) {
			conditions.add(cb.equal(root.get(CategoryProduct_.categoryLevel), level));
		}
	
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<CategoryProduct> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	
	public Optional<CategoryProduct> findByCategoryDesc(String categoryTypeCode, String categoryDesc, String locale) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryProduct> cq = cb.createQuery(CategoryProduct.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		
		Join<CategoryProduct, CategoryType> categoryType = root.join(CategoryProduct_.categoryType);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = root.join(CategoryProduct_.attributes);
		//Join<CategoryProduct, Hierarchy> categoryHierarchy = root.join(CategoryProduct_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
		if(!(categoryDesc == null)) {
			conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.categoryDesc), categoryDesc));
		}
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<CategoryProduct> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
		
	public Optional<CategoryProduct> findByCategoryCode(String categoryTypeCode, String categoryCode, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<CategoryProduct> cq = cb.createQuery(CategoryProduct.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		Join<CategoryProduct, CategoryType> categoryType = root.join(CategoryProduct_.categoryType);
		
		//Join<CategoryProduct, Hierarchy> categoryHierarchy = root.join(CategoryProduct_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		//conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
		if(!(categoryCode == null)) {
			conditions.add(cb.equal(root.get(CategoryProduct_.categoryCode), categoryCode));
		}
		
		TypedQuery<CategoryProduct> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(false)
		);
		
		return Optional.ofNullable(query.getSingleResult());
	}
	
	public List<CategoryProduct> findByParent(String hieararchyCode, String categoryTypeCode, Long parentCategoryId, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryProduct> cq = cb.createQuery(CategoryProduct.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		
		Join<CategoryProduct, CategoryType> categoryType = root.join(CategoryProduct_.categoryType);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = root.join(CategoryProduct_.attributes);
		Join<CategoryProduct, Hierarchy> categoryHierarchy = root.join(CategoryProduct_.hierarchy);
		Join<CategoryProduct, Category> parent = root.join(CategoryProduct_.parent);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
	
		if(!(parentCategoryId == null)) {
			conditions.add(cb.equal(parent.get(CategoryProduct_.categoryId), parentCategoryId));
		}
		
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<CategoryProduct> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	
	@Override
	public List<CategoryProduct> findChildrenByCriteria(String hieararchyCode, String categoryTypeCode, String parentCategoryDesc, List<String> brandCodes, List<String> tagCodes, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryProduct> cq = cb.createQuery(CategoryProduct.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		
		Join<CategoryProduct, CategoryType> categoryType = root.join(CategoryProduct_.categoryType);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = root.join(CategoryProduct_.attributes);
		Join<CategoryProduct, Hierarchy> categoryHierarchy = root.join(CategoryProduct_.hierarchy);
		Join<CategoryProduct, Product> product = root.join(CategoryProduct_.products);
		Join<Product, Brand> brand = product.join(Product_.brand);
		Join<CategoryProduct, Category> parent = root.join(CategoryProduct_.parent);
		Join<Category, CategoryAttribute> parentCategoryAttribute = parent.join(CategoryProduct_.attributes);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
		if(!brandCodes.isEmpty()) {
			conditions.add(brand.get(Brand_.brandCode).in(brandCodes));
		}
		if(!tagCodes.isEmpty()) {
			Join<Product, ProductTag> tags = product.join(Product_.tags);
			conditions.add(tags.get(ProductTag_.productTagCode).in(tagCodes));
		}
		if(!(parentCategoryDesc == null)) {
			conditions.add(cb.equal(parentCategoryAttribute.get(CategoryAttribute_.categoryDesc), parentCategoryDesc));
		}
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<CategoryProduct> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}
	
	public List<CategoryProduct> findByLevel(String hieararchyCode, String categoryTypeCode, Long level, String locale) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<CategoryProduct> cq = cb.createQuery(CategoryProduct.class);
		
		Root<CategoryProduct> root = cq.from(CategoryProduct.class);
		
		Join<CategoryProduct, CategoryType> categoryType = root.join(CategoryProduct_.categoryType);
		Join<CategoryProduct, CategoryAttribute> categoryAttribute = root.join(CategoryProduct_.attributes);
		Join<CategoryProduct, Hierarchy> categoryHierarchy = root.join(CategoryProduct_.hierarchy);
		
		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(categoryType.get(CategoryType_.code), categoryTypeCode));
		conditions.add(cb.equal(categoryHierarchy.get(Hierarchy_.code), hieararchyCode));
		if(!(level == null)) {
			conditions.add(cb.equal(root.get(CategoryProduct_.categoryLevel), level));
		}
	
		conditions.add(cb.equal(categoryAttribute.get(CategoryAttribute_.lclCd), locale));
		
		TypedQuery<CategoryProduct> query = em.createQuery(cq
				.select(root)
				.where(conditions.toArray(new Predicate[] {}))
				.distinct(true)
		);
		
		return query.getResultList();
	}

	@Override
	public List<CategoryProduct> getAll() {
		// TODO Auto-generated method stub
		return this.findAll();
	}
	
	
	
}
